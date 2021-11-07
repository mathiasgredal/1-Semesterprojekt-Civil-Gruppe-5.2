package worldofzuul;

import java.util.*;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import worldofzuul.Config.*;
import worldofzuul.Items.*;
import worldofzuul.Items.EnergyConsumer.*;
import worldofzuul.Exceptions.*;
import worldofzuul.Input.*;
import worldofzuul.Rooms.*;
import worldofzuul.Rooms.Shops.EnergyShop;
import worldofzuul.Rooms.Shops.RetailShop;
import worldofzuul.Rooms.Shops.Shop;


/**
 * The primary class for handling game logic
 */
public class Game {
    // Singleton pattern for game
    public static final Game instance = new Game();

    // Other encapsulated game attributes
    private String nameOfGame = "Greenhouse 'Jazz'";
    private Player player = new Player();
    private Recap recap = new Recap();
    private Config config;

    private int gameYear = 0;
    private final Parser parser;
    private Room currentRoom;

    private final House house = new House(1600);
    private final BuildArea buildArea = new BuildArea();
    private final ArrayList<Shop> shops = new ArrayList<>();

    /**
     * Constructor for the Game class
     */
    private Game() {
        createRooms();
        parser = new Parser();

        // Load config from file using YAML
        Constructor constructor = new Constructor(Config.class);
        Yaml yaml = new Yaml(constructor);
        config = yaml.load(FileResourcesUtils.getFileFromResourceAsStream("config.yml"));

        // Load shop data from config file
        loadShopItems();
    }

    /**
     * Creates a linked structure of rooms to navigate through
     */
    private void createRooms() {
        CrossRoad crossRoad = new CrossRoad();
        ShopArea shopArea = new ShopArea();

        Shop fossilShop = new EnergyShop("Fossil energyshop", "at a fossil energy provider, we provide energy from fossil fuels");
        Shop batteryShop = new EnergyShop("Battery shop", "at a battery shop, we sell batteries");
        Shop windShop = new EnergyShop("Windturbine shop", "at a wind shop, we sell wind turbines");
        Shop solarShop = new EnergyShop("Solar shop", "at a solar shop, we sell solar panels");
        Shop retailShop = new RetailShop("Retail store",
                "in a general purpose retail store selling everything under the sun.",
                List.of(new HeatPump(), new ElectricCar()));

        house.setExit("south", crossRoad);
        house.setExit("west", buildArea);

        crossRoad.setExit("north", house);
        crossRoad.setExit("west", retailShop);
        crossRoad.setExit("east", shopArea);
        crossRoad.setExit("south", fossilShop);

        buildArea.setExit("east", house);
        retailShop.setExit("east", crossRoad);
        fossilShop.setExit("north", crossRoad);

        shopArea.setExit("west", crossRoad);
        shopArea.setExit("north", windShop);
        shopArea.setExit("east", solarShop);
        shopArea.setExit("south", batteryShop);

        windShop.setExit("south", shopArea);
        solarShop.setExit("west", shopArea);
        batteryShop.setExit("north", shopArea);

        shops.add(fossilShop);
        shops.add(windShop);
        shops.add(batteryShop);
        shops.add(solarShop);
        shops.add(retailShop);

        currentRoom = house;
    }

    /**
     * Loads the shop items defined in the config yaml file into the shops
     */
    private void loadShopItems() {
        // Each array of shop items is stored in a map, with a key equaling the classname of the shop
        config.getShopItems().forEach((k, v) -> {
            for (Shop shop : shops) {
                if (shop.getName().equals(k)) {
                    shop.setShopItems(new ArrayList<>(Arrays.asList(v)));
                }
            }
        });
    }

    /**
     * Main entrypoint to game
     */
    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing!.  Good bye.");
    }

    /**
     * Prints the welcome message
     */
    private void printWelcome() {
        System.out.println();
        System.out.printf("Welcome to the %s!", nameOfGame);
        System.out.printf("\n%s is a new, incredibly awesome adventure game.", nameOfGame);
        System.out.println("\nType '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        currentRoom.printEnterRoomString(this);
    }

    /**
     * Handles the delegation of logic behind each command from the player
     *
     * @param command The tokenized player input
     * @return A boolean indicating whether the program should end
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case HELP -> printHelp();
            case UNKNOWN -> System.out.println("I don't know what you mean...Please try again.");
            case BUY -> {
                if (currentRoom instanceof Shop) {
                    buyItem(command, (Shop) currentRoom);
                } else {
                    System.out.println("You are not currently in a shop.");
                }
            }
            case LOOK -> lookAt(command);
            case NEXT -> wantToQuit = nextYear(command);
        }

        return wantToQuit;
    }

    /**
     * Allows inspections of rooms and objects in the world
     *
     * @param command Player input
     */
    private void lookAt(Command command) {
        if (!command.hasSecondWord()) {
            currentRoom.printEnterRoomString(this);
        } else {
            currentRoom.getInfoAbout(command.getSecondWord());
        }
    }

    /**
     * Prints the help message
     */
    private void printHelp() {
        System.out.println("You are in your house.");
        System.out.println("Now go fight climate change and save some moeny!");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Allows navigation between rooms
     *
     * @param command Player input
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is nothing here.");
        } else {
            currentRoom = nextRoom;
            currentRoom.printEnterRoomString(this);
        }
    }

    /**
     * Allows the player to buy items from the shop they are currently in
     *
     * @param command     Player input
     * @param currentShop The current shop the player is in
     */
    private void buyItem(Command command, Shop currentShop) {
        if (!command.hasSecondWord()) {
            System.out.println("Buy what?");
            return;
        }

        //Try-catch to handle exceptions that can be created by the user.
        try {
            String itemName = command.getSecondWord();
            Buyable item = currentShop.getShopItem(Integer.parseInt(itemName) - 1);

            if (player.withdrawMoney(item.getPrice())) {
                System.out.println("You have bought: " + item.getName());
                System.out.printf("Current balance: $%.2f\n", player.getPlayerEconomy());

                if (item instanceof EnergySource) {
                    buildArea.addEnergySource((EnergySource) item);
                } else if (item instanceof EnergyConsumer) {
                    house.addEnergyConsumer((EnergyConsumer) item);
                } else {
                    player.insertMoney(item.getPrice());
                    throw new RecieverForBoughtItemNotFound();
                }
            } else {
                System.out.println("You do not have enough money to buy this item, you need: " + (itemFromShop.getEnergyPrice() - player.getPlayerEconomy()));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please insert a number between," + " 1 and " + currentShop.getShopItems().size());
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number.");
        } catch (RecieverForBoughtItemNotFound recieverForBoughtItemNotFound) {
            System.out.println("Could not buy that item");
        } catch (CannotBuyItemMoreThanOnce cannotBuyItemMoreThanOnce) {
            System.out.println(cannotBuyItemMoreThanOnce.getMessage());
        }
    }

    /**
     * Getter for the main game year
     *
     * @return The current game year/round starting from 0
     */
    public int getGameYear() {
        return gameYear;
    }

    /**
     * Allows the player to progress to the next year,
     * assuming they have fulfilled the requirements
     * to do so.
     *
     * @param command The player input
     * @return A boolean value indicating whether the game has finished
     */
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
            double soldEnergyPrice = excessEnergy * buildArea.getEnergySalesPricePrkWh();
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

    /**
     * Allows printing of the recap message, when the game has ended
     */
    private void printRecap() {
        System.out.println("Time is up, you have finished the game!");
        System.out.println("TODO: Implement recap page");
    }

    /**
     * Allows the player to quit the game with the quit command
     *
     * @param command player input
     * @return A boolean value indicating whether the game should end
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * A getter for the player attribute
     *
     * @return A reference to the player object
     */
    public Player getPlayer() {
        return player;
    }
}
