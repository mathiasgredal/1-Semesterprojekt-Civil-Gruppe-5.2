package worldofzuul.Rooms;

import worldofzuul.Game;

import java.util.HashMap;
import java.util.Set;

/**
 * The base class for making rooms and handles navigation logic
 */
public abstract class Room {
    private final String name;
    private final String description;
    private final HashMap<String, Room> exits;

    /**
     * Constructor for the Room class
     *
     * @param name        The named of the room, used for indicating exit path names
     * @param description Printed when you enter a room and look around the room
     */
    public Room(String name, String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.name = name;
    }

    /**
     * Getter for the room name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter to the room description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Links another room with an associated direction
     *
     * @param direction The navigation direction used to exit the room, with the command "go <direction>"
     * @param neighbor  A link to the new room
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Gets the description without the appended "You are"
     */
    public String getShortDescription() {
        return getDescription();
    }

    /**
     * Gets a description appended with "You are"
     */
    public String getLongDescription() {
        return "You are " + description + ".\n";
    }

    /**
     * Returns a string descriping the possible exits for the room
     */
    public String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString.append(" ").append(exit);
            returnString.append("(").append(exits.get(exit).name).append(")");
        }
        return returnString.toString();
    }

    /**
     * Gets the room associated with a certain direction
     *
     * @param direction The direction which should only be north, east, south or west
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Called when the player enters a room, and must be implemented by subclasses
     *
     * @param game A reference to the primary game class(this is not optimal)
     */
    public abstract void printEnterRoomString(Game game);

    /**
     * Used for inspecting things in a room. Subclasses are expected, but not required, to override this method.
     * If the subclass doesn't find anything inspectable in the room, then it should call this method.
     *
     * @param secondWord The thing you want to inspect
     */
    public void getInfoAbout(String secondWord) {
        System.out.println("I don't know what \"" + secondWord + "\" is.");
    }
}

