package worldofzuul.Rooms.Shops;

import worldofzuul.Items.Buyable;
import worldofzuul.Game;
import worldofzuul.Items.EnergyConsumer.EnergyConsumer;
import worldofzuul.Items.EnergySource;
import worldofzuul.Rooms.Room;

import java.util.List;

public abstract class Shop extends Room {
    private List<Buyable> shopItems;

    /**
     * Constructor with a list of shop items that has implemented Buyable.
     * @see worldofzuul.Items.Buyable
     *
     * @param name          Shop name, is used by the config file to find specific shop items.
     * @param description   Shop description, is used in the 1st iteration to show which shop the player had entered
     * @param shopItems     Shop items, a list of shop items, loaded from the config file.
     */
    public Shop(String name, String description, List<Buyable> shopItems) {
        super(name, description);
        this.shopItems = shopItems;
    }

    /**
     * {@inheritDoc}
     */
    public Shop(String name, String description) {
        this(name, description, List.of());
    }

    /**
     * Gets an energysoruce or energyconsumer from an index in the shopItems list.
     *
     * @param index List index, searches shopItems, at the given index.
     * @return      An element in the list of Buyable's
     */
    public Buyable getShopItem(int index) {
        return shopItems.get(index);
    }

    /**
     * Sets the shopItems list, to the given list.
     *
     * @param shopItems Shop items, a list of Buyable's.
     */
    public void setShopItems(List<Buyable> shopItems) {
        this.shopItems = shopItems;
    }

    /**
     * Gets the list of shop items.
     *
     * @return The entire list of shopItems
     */
    public List<Buyable> getShopItems() {
        return shopItems;
    }

    /**
     * Prints all the information for all the shop items, this will print the price, emission and output.
     * @see EnergySource#getInfo()
     * @see EnergyConsumer#getInfo()
     *
     * @param availableMoney Available money, this is the players' money left, this will be used to check if the text should be red or green.
     */
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

    /**
     * {@inheritDoc}
     */
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
