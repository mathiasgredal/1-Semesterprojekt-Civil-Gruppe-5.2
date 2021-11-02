package worldofzuul;

import worldofzuul.EnergySources.*;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Input.Parser;
import worldofzuul.Rooms.House;
import worldofzuul.Rooms.Room;
import worldofzuul.Rooms.Shop;

import java.util.ArrayList;

public class Game {
    private final Parser parser;
    Player player = new Player(120000, new ArrayList<>());
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {
        House house = new House("in your house", 1600);
        Shop fossilShop = new Shop("in a shop, where they sell power from fossil fuels", new EnergySource[]{new GasEnergy("Cenovous Energy Inc", 50000, 1342, 1600), new CoalEnergy("EOG Resources Inc", 62000, 1976, 1750)});
        Shop renewableShop = new Shop("in a shop, where you can buy renewable energy soruces", new EnergySource[]{new HydroEnergy("Watermill", 120000, 0, 600), new SolarEnergy("Solar Panel", 1300000, 0, 1800)});


        house.setExit("west", renewableShop);
        house.setExit("east", fossilShop);

        renewableShop.setExit("east", house);

        fossilShop.setExit("west", house);

        currentRoom = house;
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription() + currentRoom.getExitString());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case HELP -> printHelp();
            case UNKNOWN -> System.out.println("I don't know what you mean...");
            case BUY -> {
                if (currentRoom instanceof Shop) {
                    buyItem(command, (Shop) currentRoom);
                } else {
                    System.out.println("You are not currently in a shop");
                }
            }
        }

        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);


        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            currentRoom.printEnterRoomString(this);
        }
    }

    private void buyItem(Command command, Shop currentShop) {
        if (!command.hasSecondWord()) {
            System.out.println("Buy what?");
            return;
        }

        //Try-catch to handle exceptions that can be created by the user.
        try {
            String item = command.getSecondWord();
            EnergySource itemFromShop = currentShop.getShopItem(Integer.parseInt(item) - 1);

            if (itemFromShop.getEnergyPrice() <= player.getPlayerEconomy()) {
                //Adds the bought energy source to the players' arraylist.
                System.out.println("You have bought: " + itemFromShop.getEnergyName());
                player.addEnergySource(itemFromShop);
                player.setPlayerEconomy(player.getPlayerEconomy() - itemFromShop.getEnergyPrice());
                System.out.println(player.getPlayerEconomy());
            } else {
                System.out.println("Not enough money to buy this item, you need: " + (itemFromShop.getEnergyPrice() - player.getPlayerEconomy()));
            }

        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please insert a number between," + " 1 and " + currentShop.getShopItems().length);
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number");
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    public Player getPlayer() {
        return player;
    }
}
