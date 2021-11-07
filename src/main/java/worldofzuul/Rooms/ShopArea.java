package worldofzuul.Rooms;

import worldofzuul.Game;

/**
 * The class for shop area, which is for accessing various shops
 */
public class ShopArea extends Room {
    /**
     * No-arg constructor for shop area
     */
    public ShopArea() {
        super("shop area", "a shopping area, where you can enter different shops");
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
