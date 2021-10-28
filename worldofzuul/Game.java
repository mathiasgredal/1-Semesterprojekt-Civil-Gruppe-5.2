package worldofzuul;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Game
{
    Player player = new Player(2323, new ArrayList());
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        //TODO: Create a statement that checks every time there is a room change, if the currentRoom is from the Shop class, if so print the shops details.
        /**
         * Example of how to instantiate the newly made shop class.
         * Shop outside = new Shop("in a shop, where they sell power from fossil fuels", new EnergySource[]{new GasEnergy("Cenovous Energy Inc", 50000, 1342, 1600), new CoalEnergy("EOG Resources Inc", 62000, 1976, 1750)});
        **/

        ArrayList<Room> rooms = new ArrayList<>();
        House house = new House("in your house", 1600);
        Shop fossilShop = new Shop("in a shop, where they sell power from fossil fuels", new EnergySource[]{new GasEnergy("Cenovous Energy Inc", 50000, 1342, 1600), new CoalEnergy("EOG Resources Inc", 62000, 1976, 1750)});
        Shop renewableShop = new Shop("in a shop, where you can buy renewable energy soruces", new EnergySource[]{new HydroEnergy("Watermill", 120000, 0, 600)});

        rooms.add(house);
        rooms.add(fossilShop);
        rooms.add(renewableShop);

        
        house.setExit("west", renewableShop);
        house.setExit("east", fossilShop);

        renewableShop.setExit("east", house);

        fossilShop.setExit("west", house);

        currentRoom = house;
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        //Checks if the player wants to buy an item from one of the shops.
        else if (commandWord == CommandWord.BUY){
            if(currentRoom instanceof Shop){
                buyItem(command, (Shop) currentRoom);
            }
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);


        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;

            //Checks if the current room the player is in, is a child of the Shop class.
            if(currentRoom instanceof Shop){
                System.out.println(currentRoom.getShortDescription());

                //Lists the available items, that was added in createRoom
                System.out.println("Available items: ");
                ((Shop) currentRoom).printShopDetails();

                //Gets the exits out of the room.
                System.out.println("\n" + currentRoom.getExitString());
            }
            else{
                System.out.println(currentRoom.getShortDescription());
            }
        }
    }

    private void buyItem(Command command, Shop currentShop){
        if(!command.hasSecondWord()) {
            System.out.println("Buy what?");
            return;
        }

        //Try-catch to handle exceptions that can be created by the user.
        try {
            String item = command.getSecondWord();

            System.out.println("You have bought: " + currentShop.getShopItem(Integer.valueOf(item) - 1).getEnergyName());

            //Adds the bought energy source to the players' arraylist.
            player.addEnergySource(currentShop.getShopItem(Integer.valueOf(item) - 1));

        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("Please insert a number between," + " 1 and " + currentShop.getShopItems().length);
        }
        catch (NumberFormatException ex){
            System.out.println("Please enter a valid number");
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
