package worldofzuul.Rooms;

import worldofzuul.Game;

/**
 * The crossroad is the main path to the different parts of the world
 */
public class CrossRoad extends Room {

    /**
     * Initialises the name and description of the crossroad using a no-arg constructor
     */
    public CrossRoad() {
        super("crossroad", "a crossroad, where you can travel to different parts of the world");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());
        System.out.println(getExitString());
    }
}
