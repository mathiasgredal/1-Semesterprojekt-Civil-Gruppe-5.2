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
    private Config config;

    private double soldEnergyPrice;

    private int gameYear = 0;
    private final Parser parser;
    private Room currentRoom;

    private final House house = new House(4400);
    private final BuildArea buildArea = new BuildArea();

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public House getHouse() {
        return house;
    }

    public double getSoldEnergyPrice() {
        return soldEnergyPrice;
    }

    private final ArrayList<Shop> shops = new ArrayList<>();

    /**
     * Constructor for the Game class
     */
    private Game() {
        createRooms();
        parser = new Parser();

        // Load config from file using YAML
        try {
            Constructor constructor = new Constructor(Config.class);
            Yaml yaml = new Yaml(constructor);
            config = yaml.load(FileResourcesUtils.getFileFromResourceAsStream("config.yml"));

            // Load shop data from config file
            loadShopItems();
        } catch (CannotFindResourceException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
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
        printIntroduction();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing!.  Good bye.");
    }

    /**
     * Prints the introduction message
     */
    private void printIntroduction() {
        System.out.println();
        System.out.println(" __________________________________________________________________________________________________________________ ");
        System.out.printf("| Welcome to the %s!                                                                                |", nameOfGame);
        System.out.println("""

                | INTRODUCTION:                                                                                                    |
                | The year is 2010. Everyday greenhouse gasses are being emitted into the atmosphere, resulting in global warming! |
                | The United Nations has come up with a goal to reduce greenhouse gas emissions by producing the majority of elec- |
                | tricity using renewable energy sources before 2030. Right now your energy needs are fulfilled by fossil fuels,   |
                | and that is emitting a lot of CO2 int the atmosphere. Every year you will get a salary and perform actions until |
                | the next year. In the game there will be shops, where you can buy energy from different sources. Excess energy   |
                | created will be added to your total amount of money. You have an energy need you must fulfill to move on the the |
                | next year. If you cannot produce enough energy og run out of money, you lose.                                    |
                |                                                                                                                  |
                | GOALS:                                                                                                           |
                |   - Make the most money of your situation.                                                                       |
                |   - Emit as little of CO2 as possible.                                                                          |
                |   - Produce as much energy as possible.                                                                          |
                | Good Luck!                                                                                                       |""");
        System.out.println("|__________________________________________________________________________________________________________________|");
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
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
            case HELP -> printHelp();
            case UNKNOWN -> System.out.println("I don't know what you mean...Please try again.");
            case BUY -> {
                if (currentRoom instanceof Shop) {
                    try {
                        buyItem(command, (Shop) currentRoom);
                    } catch (CannotBuyItemMoreThanOnceException e) {
                        System.out.println(e.getMessage());
                    }
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
    public void lookAt(Command command) {
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
    public void goRoom(Command command) {
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
    public void buyItem(Command command, Shop currentShop) throws CannotBuyItemMoreThanOnceException {
        if (!command.hasSecondWord()) {
            System.out.println("Buy what?");
            return;
        }

        //Try-catch to handle exceptions that can be created by the user.
        try {
            String itemName = command.getSecondWord();
            Buyable item = currentShop.getShopItem(Integer.parseInt(itemName) - 1);

            if (player.withdrawMoney(item.getPrice())) {
                if (item instanceof EnergySource) {
                    buildArea.addEnergySource((EnergySource) item);
                } else if (item instanceof EnergyConsumer) {
                    house.addEnergyConsumer((EnergyConsumer) item);
                } else {
                    player.insertMoney(item.getPrice());
                    throw new ReceiverForBoughtItemNotFoundException();
                }
                System.out.println("You have bought: " + item.getName());
                System.out.printf("Current balance: $%.2f\n", player.getPlayerEconomy());
            } else {
                System.out.println("You do not have enough money to buy this item, you need: " + (item.getPrice() - player.getPlayerEconomy()));
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Please insert a number between," + " 1 and " + currentShop.getShopItems().size());
        } catch (NumberFormatException ex) {
            System.out.println("Please enter a valid number.");
        } catch (ReceiverForBoughtItemNotFoundException recieverForBoughtItemNotFound) {
            System.out.println("Could not buy that item");
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
            if (gameYear == 20) {
                printRecap();
                return true;
            }

            // Step 1: Calculate values
            double excessEnergy = buildArea.getYearlyEnergyProduction() - house.getEnergyRequirement();
            soldEnergyPrice = excessEnergy * buildArea.getEnergySalesPricePrkWh();
            double emissions = buildArea.getYearlyEmissions() + house.getYearlyEmissions();

            // Step 2: Insert yearly salery and energy sales to player balance
            player.insertMoney(soldEnergyPrice + player.getYearlyIncome());
            player.withdrawMoney(house.getYearlyCost());

            // Step 3: Log stuff for recap
            player.transferEnergySources(getGameYear(), new ArrayList<>(buildArea.getEnergySources()), house.getYearlyEmissions());

            // Step 5: Remove fossil fuels
            buildArea.removeFossilEnergySources();

            // Step 6: Add earned money to each energysource
            buildArea.addYearlyEnergyProductionToEnergySources();

            // Step 7: Increment year
            gameYear++;

            // Step 8: Print to the player what happened(money, co2, sold energy, sales price)
            System.out.println("You are now in the year: " + gameYear);
            System.out.println("Your emission for this year is: " + player.getRecapEnergyEmission().get(gameYear - 1));
            System.out.println("Your total emission is: " + player.calculateEmission());
            System.out.println("Your earned money on sold energy: " + soldEnergyPrice);
            System.out.println("Your balance are:" + player.getPlayerEconomy());
            return false;
        } else {
            //checks if it is possible for the player to buy the required energy
            System.out.printf("Please fulfill the required amount of energy, missing: %.2fkWh\n", house.getEnergyRequirement() - buildArea.getYearlyEnergyProduction());

            double energyDeficit = house.getEnergyRequirement() - buildArea.getYearlyEnergyProduction();

            boolean foundSolution = false;
            for (var shop : shops) {
                for (var source : shop.getShopItems()) {
                    if (source instanceof EnergySource) {
                        double maxOutput = ((EnergySource) source).getOutput() * player.getPlayerEconomy() / source.getPrice();
                        if (maxOutput > energyDeficit)
                            foundSolution = true;
                    }
                }
            }

            if (!foundSolution) {
                System.out.println("You have died!");
                return true;
            }
        }
        return false;
    }


    public void printRecap() {
        int highScore = 0;
        highScore += player.getPlayerEconomy() * 34 + buildArea.getYearlyEnergyProductionRenewable() * 21 + player.calculateEmission() * 10;

        String recapString = "RECAP";
        String eMoney = "Excess Money: ";
        String energyOutputString = "Total Energy Output: ";
        String emissionString = "Total CO2 Emission: ";
        String hsString = "High Score: ";

        ArrayList<Integer> textLengths = new ArrayList<>();

        textLengths.add(recapString.length());
        textLengths.add(eMoney.length() + String.valueOf(player.getPlayerEconomy()).length());
        textLengths.add(energyOutputString.length() + String.valueOf(buildArea.getYearlyEnergyProductionRenewable()).length());
        textLengths.add(emissionString.length() + String.valueOf(player.calculateEmission()).length());
        textLengths.add(hsString.length() + String.valueOf(highScore).length());


        int longestString = Collections.max(textLengths) + 4;
        int distToEdge = longestString / 3;
        int lineLength = longestString + distToEdge * 2 - 2;

        int counter;
        System.out.println(longestString);
        for (int i = 0; i <= 12; i++) {

            switch (i) {
                case 0 -> {
                    System.out.print(" ");
                    for (counter = 0; counter <= lineLength; counter++) {
                        System.out.print("_");
                    }
                    System.out.println();
                }
                case 1, 4, 6, 8, 10 -> {
                    System.out.print("|");
                    for (counter = 0; counter <= lineLength; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
                case 2 -> {
                    System.out.print("|");
                    for (counter = 0; counter < lineLength / 2 - recapString.length() / 2; counter++) {
                        System.out.print(" ");
                    }
                    System.out.print(recapString);
                    for (counter = 0; counter < lineLength / 2 - recapString.length() / 2; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
                case 3 -> {
                    int eMoneyAndNr = eMoney.length() + String.valueOf(player.getPlayerEconomy()).length();
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.print(eMoney + player.getPlayerEconomy());
                    for (counter = 0; counter < lineLength - eMoneyAndNr - distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
                case 5 -> {
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.print(energyOutputString + buildArea.getYearlyEnergyProductionRenewable());
                    int energyAndNr = energyOutputString.length() + String.valueOf(buildArea.getYearlyEnergyProductionRenewable()).length();
                    for (counter = 0; counter < lineLength - energyAndNr - distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
                case 7 -> {
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++) {
                        System.out.print(" ");
                    }

                    System.out.print(emissionString + player.calculateEmission());
                    int emissionAndNr = emissionString.length() + String.valueOf(player.calculateEmission()).length();
                    for (counter = 0; counter < lineLength - emissionAndNr - distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
                case 9 -> {
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.print(hsString + highScore);
                    int hsStringAndNr = hsString.length() + String.valueOf(highScore).length();
                    for (counter = 0; counter < lineLength - hsStringAndNr - distToEdge; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                }
                case 11 -> {
                    System.out.print("|");
                    for (counter = 0; counter <= lineLength; counter++) {
                        System.out.print("_");
                    }
                    System.out.println("|");
                }
            }

        }
    }

    /**
     * Allows the player to quit the game with the quit command
     *
     * @param command player input
     * @return A boolean value indicating whether the game should end
     */
    public boolean quit(Command command) {
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

    /**
     * A getter for the build area
     */
    public BuildArea getBuildArea() {
        return buildArea;
    }
}
