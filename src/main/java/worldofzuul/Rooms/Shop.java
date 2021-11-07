package worldofzuul.Rooms;

import worldofzuul.Items.Buyable;
import worldofzuul.Game;

import java.util.List;

public class Shop<T extends Buyable> extends Room {
    private String shopName;
    protected List<T> shopItems;

    public Shop(String name, String description, List<T> shopItems) {
        super(name, description);
        this.shopItems = shopItems;
    }

    public Shop(String name, String description) {
        this(name, description, List.of());
    }

    public T getShopItem(int index) {
        return shopItems.get(index);
    }

    public void setShopItems(List<T> shopItems) {
        this.shopItems = shopItems;
    }

    public List<T> getShopItems() {
        return shopItems;
    }

    public void printShopDetails(double availableMoney) {
        for (int i = 0; i < shopItems.size(); i++) {
            System.out.print(availableMoney > shopItems.get(i).getPrice() ? "\u001B[32m" : "\u001B[31m");
            System.out.println("[" + (i + 1) + "] " + shopItems.get(i).getInfo());
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
