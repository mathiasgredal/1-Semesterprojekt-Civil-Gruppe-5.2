package worldofzuul.Rooms;

import worldofzuul.Game;

import java.util.HashMap;
import java.util.Set;

public abstract class Room {
    private final String description;
    private final HashMap<String, Room> exits;
    private final String name;

    public Room(String description, String name) {
        this.description = description;
        this.exits = new HashMap<>();
        this.name = name;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n";
    }

    public String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString.append(" ").append(exit);
            returnString.append("(").append(exits.get(exit).name).append(")");
        }
        return returnString.toString();
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public abstract void printEnterRoomString(Game game);

    public void getInfoAbout(String secondWord) {
        System.out.println("I don't know what \"" + secondWord + "\" is.");
    }
}

