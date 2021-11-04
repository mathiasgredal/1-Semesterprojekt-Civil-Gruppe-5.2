package worldofzuul.Rooms;

import worldofzuul.Game;

public class CrossRoad extends Room{
    public CrossRoad() {
        super("a crossroad, where you can travel to different parts of the world");
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());
        System.out.println(getExitString());
    }
}
