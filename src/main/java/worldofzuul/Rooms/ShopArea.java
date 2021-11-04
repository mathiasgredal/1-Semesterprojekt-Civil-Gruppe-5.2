package worldofzuul.Rooms;

import worldofzuul.Game;

public class ShopArea extends Room{
    public ShopArea() {
        super("a shopping area, where you can enter different shops");
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());
        System.out.println(getExitString());
    }
}
