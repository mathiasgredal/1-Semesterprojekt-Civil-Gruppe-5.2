package worldofzuul.Rooms.Shops;

import worldofzuul.Items.Buyable;
import worldofzuul.Game;
import worldofzuul.Rooms.Room;

import java.util.List;

public abstract class Shop extends Room {
    private List<Buyable> shopItems;

    public Shop(String name, String description, List<Buyable> shopItems) {
        super(name, description);
        this.shopItems = shopItems;
    }

    public Shop(String name, String description) {
        this(name, description, List.of());
    }

    public Buyable getShopItem(int index) {
        return shopItems.get(index);
    }

    public void setShopItems(List<Buyable> shopItems) {
        this.shopItems = shopItems;
    }

    public List<Buyable> getShopItems() {
        return shopItems;
    }

    public void printShopDetails(double availableMoney) {
        for (int i = 0; i < shopItems.size(); i++) {
            // This statement, prints ansi escape codes to color the shop item red or green ¨
            // depending on whether the player can afford the item
            System.out.print(availableMoney > shopItems.get(i).getPrice() ? "\u001B[32m" : "\u001B[31m");
            System.out.println("[" + (i + 1) + "] " + shopItems.get(i).getInfo());
            // This escape code resets the color
            System.out.print("\u001B[0m");
        }
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());

        System.out.println("Your available balance: " + "$" + game.getPlayer().getPlayerEconomy());
        //Lists the available items, that was added in createRoom
        System.out.println("Available items: ");
        printShopDetails(game.getPlayer().getPlayerEconomy());

        //Gets the exits out of the room.
        System.out.println("\n" + getExitString());
    }
}
