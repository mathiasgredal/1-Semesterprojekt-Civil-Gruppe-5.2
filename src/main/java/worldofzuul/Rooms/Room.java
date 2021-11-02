package worldofzuul.Rooms;

import worldofzuul.*;
import java.util.Set;
import java.util.HashMap;


public abstract class Room
{
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n";
    }

    public String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString.append(" ").append(exit);
        }
        return returnString.toString();
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public abstract void printEnterRoomString(Game game);
}

