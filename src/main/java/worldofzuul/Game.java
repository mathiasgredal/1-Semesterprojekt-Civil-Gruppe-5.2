package worldofzuul;

import worldofzuul.EnergySources.*;
import worldofzuul.Input.*;
import worldofzuul.Rooms.*;

import java.util.ArrayList;

public class Game {
    String nameOfGame = "Greenhouse 'Jazz'";
    Player player = new Player(120000, new ArrayList<>());
    private int gameYear = 0;
    private Parser parser;
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        House house = new House("in your house", 1600);
        Shop fossilShop = new Shop("in a shop, where they sell power from fossil fuels", new EnergySource[]{new GasEnergy(), new CoalEnergy()});
        Shop renewableShop = new Shop("in a shop, where you can buy renewable energy sources", new EnergySource[]{new HydroEnergy(), new SolarEnergy()});

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
        System.out.printf("Welcome to the %s!", nameOfGame);
        System.out.printf("\n%s is a new, incredibly awesome adventure game.", nameOfGame);
        System.out.println(" Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription()+currentRoom.getExitString());
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
            case NEXT -> nextYear(command);
        }

        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are in your house");
        System.out.println("now go fight climate change");
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

    public int getGameYear() {
        return gameYear;
    }

    public void nextYear(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Next year?");
            return;
        }

        try {
            if (command.getSecondWord().contains("year")) {
                if (player.getTotalEnergyOutput() >= ((House) currentRoom).getEnergyNeed()) {
                    gameYear++;
                    System.out.println("You are now in the year: " + (2010 + getGameYear()));
                    player.clearEnergySources(getGameYear());
                } else {
                    System.out.println("Please fulfill the required amount of energy");
                }
            }
        } catch (ClassCastException ex) {
            if (currentRoom instanceof Shop) {
                System.out.println("Unable to do this action, outside of your house");
            } else {
                System.out.println("Failure to proceed");
            }
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
