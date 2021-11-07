package worldofzuul.Rooms;

import worldofzuul.Game;

public class Paths extends Room {
    public Paths(String description, String name) {
        super(name, description);
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());

        //Print exits, the rooms you can go to.
        System.out.println(getExitString());
    }
}
