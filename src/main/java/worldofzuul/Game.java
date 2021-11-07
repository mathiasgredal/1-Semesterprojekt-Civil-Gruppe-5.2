package worldofzuul;

import worldofzuul.EnergySources.*;
import worldofzuul.Input.*;
import worldofzuul.Rooms.*;
import java.util.Collections;
import java.util.ArrayList;

public class Game {
    public static final Game instance = new Game(); //Singleton pattern #1
    String nameOfGame = "Greenhouse 'Jazz'";
    Player player = new Player(120000, new ArrayList<>());
    private int gameYear = 0;
    private Parser parser;
    private Room currentRoom;
    ArrayList<EnergySource> energySourcesPrices = new ArrayList<>();

    private Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        House house = new House("in your house", "House", 1600);

        Paths pathEast = new Paths("You are on a path, where you can go to energy companies, that uses fossil fuels", "Path to fossil fuels");
        Paths pathWest = new Paths("You are on a path, where you can go to energy companies, that uses renewable fuels", "Path to renewable fuels");

        Shop gasProvider = new Shop("in a shop, where they sell power from gas energy", "Shop for gas-energy", new EnergySource[]{new GasEnergy()});
        Shop coalProvider = new Shop("in a shop, where they sell power from coal energy", "Shop for coal-energy", new EnergySource[]{new CoalEnergy()});
        Shop oilProvider = new Shop("in a shop, where they sell power from oil energy", "Shop for oil-energy", new EnergySource[]{new OilEnergy()});

        //Shop hydroProvider = new Shop("in a shop, where you can buy renewable energy sources", new EnergySource[]{new HydroEnergy(), new SolarEnergy()});
        Shop windProvider = new Shop("in a shop, where you can buy windmills", "Shop for wind-energy",  new EnergySource[]{new WindEnergy()});
        Shop solarProvider = new Shop("in a shop, where you can buy solar panels", "Shop for solar-energy", new EnergySource[]{new SolarEnergy()});

        energySourcesPrices.add(gasProvider.getShopItem(0));
        energySourcesPrices.add(coalProvider.getShopItem(0));
        energySourcesPrices.add(oilProvider.getShopItem(0));

        house.setExit("west", pathWest);
        house.setExit("east", pathEast);

        pathEast.setExit("south",gasProvider);
        pathEast.setExit("east", coalProvider);
        pathEast.setExit("north",oilProvider);
        pathEast.setExit("west", house);

        pathWest.setExit("south",windProvider);
        pathWest.setExit("east", house);
        pathWest.setExit("north",solarProvider);


        gasProvider.setExit("north", pathEast);
        coalProvider.setExit("west", pathEast);
        oilProvider.setExit("south", pathEast);

        windProvider.setExit("north", pathWest);
        solarProvider.setExit("south", pathWest);


        currentRoom = house;
    }

    public void play() {
        printIntroduction();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printIntroduction() {
        System.out.println();
        System.out.println(" __________________________________________________________________________________________________________________ ");
        System.out.printf("| Welcome to the %s!                                                                                |", nameOfGame);
        System.out.println("" +
                "\n| INTRODUCTION:                                                                                                    |" +
                "\n| The year is 2010. Everyday greenhouse gasses are being emitted into the atmosphere, resulting in global warming! |" +
                "\n| The United Nations has come up with a goal to reduce greenhouse gas emissions by producing the majority of elec- |" +
                "\n| tricity using renewable energy sources before 2030. Right now your energy needs are fulfilled by fossil fuels,   |" +
                "\n| and that is emitting a lot of CO2 int the atmosphere. Every year you will get a salary and perform actions until |" +
                "\n| the next year. In the game there will be shops, where you can buy energy from different sources. Excess energy   |" +
                "\n| created will be added to your total amount of money. You have an energy need you must fulfill to move on the the |" +
                "\n| next year. If you cannot produce enough energy og run out of money, you lose.                                    |" +
                "\n|                                                                                                                  |" +
                "\n| GOALS:                                                                                                           |" +
                "\n|   - Make the most money of your situation.                                                                       |" +
                "\n|   - Emmit as little of CO2 as possible.                                                                          |" +
                "\n|   - Produce as mush energy as possible.                                                                          |" +
                "\n| Good Luck!                                                                                                       |");
        System.out.println("|__________________________________________________________________________________________________________________|");
        System.out.println("\nType '" + CommandWord.HELP + "' if you need help.");
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
                    player.transferEnergySources(getGameYear());
                    gameYear++;
                    System.out.println("You are now in the year: " + (2010 + getGameYear()));
                    player.clearEnergySources(getGameYear());
                    if (getGameYear() == 20) {
                        printRecap();
                    }
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


    private void printRecap() {
        int highScore = 0;
        highScore += player.getPlayerEconomy()*34+player.getTotalEnergyOutput()*21+player.calculateEmission()*10;

        String recapString = "RECAP";
        String eMoney = "Excess Money: ";
        String energyOutputString = "Total Energy Output: ";
        String emissionString = "Total CO2 Emission: ";
        String hsString = "High Score: ";

        ArrayList<Integer> textLengths = new ArrayList<>();

        textLengths.add(recapString.length());
        textLengths.add(eMoney.length() + String.valueOf(player.getPlayerEconomy()).length());
        textLengths.add(energyOutputString.length()+String.valueOf(player.getTotalEnergyOutput()).length());
        textLengths.add(emissionString.length() + String.valueOf(player.calculateEmission()).length());
        textLengths.add(hsString.length() + String.valueOf(highScore).length());


        int longestString = Collections.max(textLengths)+4;
        int distToEdge = longestString/3;
        int lineLength = longestString+distToEdge*2-2;

        int counter;
        System.out.println(longestString);
        for (int i = 0; i <= 12; i++) {

            switch (i) {

                case 0:
                    System.out.print(" ");

                    for (counter = 0; counter <= lineLength; counter++) {
                        System.out.print("_");
                    }
                    System.out.println();
                    break;

                case 1:
                case 4:
                case 6:
                case 8:
                case 10:
                    System.out.print("|");
                    for (counter = 0; counter <= lineLength; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    break;

                case 2:
                    System.out.print("|");
                    for (counter = 0; counter < lineLength/2-recapString.length()/2; counter++) {
                        System.out.print(" ");
                    }

                    System.out.print(recapString);

                    for (counter = 0; counter < lineLength/2-recapString.length()/2; counter++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    break;

                case 3:
                    int eMoneyAndNr = eMoney.length()+String.valueOf(player.getPlayerEconomy()).length();
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++){
                        System.out.print(" ");
                    }

                    System.out.print(eMoney + player.getPlayerEconomy());


                    for (counter = 0; counter < lineLength-eMoneyAndNr-distToEdge; counter++){
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    break;


                case 5:
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++){
                        System.out.print(" ");
                    }

                    System.out.print(energyOutputString + player.getTotalEnergyOutput());

                    int energyAndNr = energyOutputString.length()+String.valueOf(player.getTotalEnergyOutput()).length();
                    for (counter = 0; counter < lineLength-energyAndNr-distToEdge; counter++){
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    break;


                case 7:
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++){
                        System.out.print(" ");
                    }

                    System.out.print(emissionString + player.calculateEmission());

                    int emissionAndNr = emissionString.length()+String.valueOf(player.calculateEmission()).length();
                    for (counter = 0; counter < lineLength-emissionAndNr-distToEdge; counter++){
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    break;

                case 9:
                    System.out.print("|");
                    for (counter = 0; counter <= distToEdge; counter++){
                        System.out.print(" ");
                    }

                    System.out.print(hsString + highScore);

                    int hsStringAndNr = hsString.length()+String.valueOf(highScore).length();
                    for (counter = 0; counter < lineLength-hsStringAndNr-distToEdge; counter++){
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    break;

                case 11:
                    System.out.print("|");

                    for (counter = 0; counter <= lineLength; counter++) {
                        System.out.print("_");
                    }
                    System.out.println("|");
                    break;
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
