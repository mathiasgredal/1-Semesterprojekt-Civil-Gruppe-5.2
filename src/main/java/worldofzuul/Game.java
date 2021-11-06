package worldofzuul;

import worldofzuul.EnergySources.EnergySource;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Input.Parser;
import worldofzuul.Rooms.*;
import worldofzuul.Rooms.Shops.*;

public class Game {
    public static final Game instance = new Game(); //Singleton pattern #1
    String nameOfGame = "Greenhouse 'Jazz'";
    Player player = new Player();
    Recap recap = new Recap();
    private int gameYear = 0;
    private final Parser parser;
    private Room currentRoom;
    private final BuildArea buildArea = new BuildArea();
    private final House house = new House(1600);

    private Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        CrossRoad crossRoad = new CrossRoad();
        ShopArea shopArea = new ShopArea();
        Shop fossilShop = new FossilShop();

        BatteryShop batteryShop = new BatteryShop();
        WindShop windShop = new WindShop();
        SolarShop solarShop = new SolarShop();

        house.setExit("south", crossRoad);

        crossRoad.setExit("north", house);
        crossRoad.setExit("west", buildArea);
        crossRoad.setExit("east", shopArea);
        crossRoad.setExit("south", fossilShop);

        buildArea.setExit("east", crossRoad);
        fossilShop.setExit("north", crossRoad);

        shopArea.setExit("west", crossRoad);
        shopArea.setExit("north", windShop);
        shopArea.setExit("east", solarShop);
        shopArea.setExit("south", batteryShop);

        windShop.setExit("south", shopArea);
        solarShop.setExit("west", shopArea);
        batteryShop.setExit("north", shopArea);

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
        System.out.println("\nType '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        currentRoom.printEnterRoomString(this);
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
            case LOOK -> lookAt(command);
            case NEXT -> wantToQuit = nextYear(command);
        }

        return wantToQuit;
    }

    private void lookAt(Command command) {
        if (!command.hasSecondWord()) {
            currentRoom.printEnterRoomString(this);
        } else {
            currentRoom.getInfoAbout(command.getSecondWord());
        }
    }

    private void printHelp() {
        System.out.println("TODO: Implement help command");
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
            String itemName = command.getSecondWord();
            EnergySource item = currentShop.getShopItem(Integer.parseInt(itemName) - 1);

            if (player.withdrawMoney(item.getEnergyPrice())) {
                System.out.println("You have bought: " + item.getEnergyName());
                System.out.printf("Current balance: $%.2f\n", player.getPlayerEconomy());
                buildArea.addEnergySource(item);
            } else {
                System.out.printf("Not enough money to buy this item, you need: %.2f\n", (item.getEnergyPrice() - player.getPlayerEconomy()));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please insert a number between," + " 1 and " + currentShop.getShopItems().size());
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number");
        }
    }

    public int getGameYear() {
        return gameYear;
    }

    public boolean nextYear(Command command) {
        if (!command.hasSecondWord() || !command.getSecondWord().contains("year")) {
            System.out.println("Next year?");
            return false;
        }

        // Is energy requirement is fulfilled?
        if (buildArea.getYearlyEnergyProduction() > house.getEnergyRequirement()) {
            // Step 0: Are we at 2030
            if (gameYear == 2030) {
                printRecap();
                return true;
            }

            // Step 1: Calculate values
            double excessEnergy = buildArea.getYearlyEnergyProduction() - house.getEnergyRequirement();
            double soldEnergyPrice = excessEnergy * buildArea.getEnergySalesPrice();
            double emissions = buildArea.getYearlyEmissions() + house.getYearlyEmissions();

            // Step 2: Insert yearly salery and energy sales to player balance
            player.insertMoney(soldEnergyPrice + player.getYearlyIncome());
            player.withdrawMoney(house.getYearlyCost());

            // Step 3: Log stuff for recap
            recap.addDataPoint(Recap.DataPoint.Emissions, gameYear, emissions);
            recap.addDataPoint(Recap.DataPoint.SoldEnergy, gameYear, excessEnergy);
            recap.addDataPoint(Recap.DataPoint.SoldEnergyPrice, gameYear, soldEnergyPrice);

            // Step 5: Remove fossil fuels
            buildArea.removeFossilEnergySources();

            // Step 6: Add earned money to each energysource
            buildArea.addYearlyEnergyProductionToEnergySources();

            // Step 7: Increment year
            gameYear++;

            // Step 8: Print to the player what happened(money, co2, sold energy, sales price)
            System.out.println("You are now in the year: " + gameYear);
            // TODO: Print the rest of what happened
            return false;
        } else {
            System.out.printf("Please fulfill the required amount of energy, missing: %.2fkWh\n", house.getEnergyRequirement() - buildArea.getYearlyEnergyProduction());

            //TODO: Perhaps we should check if it is possible for the player to buy the required energy,
            // and automatically end the game if they can't
            return false;
        }
    }

    private void printRecap() {
        System.out.println("Time is up, you have finished the game!");
        System.out.println("TODO: Implement recap page");
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
