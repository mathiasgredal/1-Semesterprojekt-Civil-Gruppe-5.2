package worldofzuul.Rooms;

import worldofzuul.EnergySources.EnergySource;
import worldofzuul.Game;

import java.util.List;

public class Shop extends Room {
    private String shopName;
    protected List<EnergySource> shopItems;

    public Shop(String name, String description) {
        super(name, description);
    }

    public EnergySource getShopItem(int index) {
        return shopItems.get(index);
    }

    public void setShopItems(List<EnergySource> shopItems) {
        this.shopItems = shopItems;
    }

    public List<EnergySource> getShopItems() {
        return shopItems;
    }

    public void printShopDetails(double availableMoney) {
        for (int i = 0; i < shopItems.size(); i++) {
            System.out.print(availableMoney > shopItems.get(i).getPrice() ? "\u001B[32m" : "\u001B[31m");
            System.out.println("[" + (i + 1) + "] " +
                    shopItems.get(i).getName() +
                    ", Costs: " + "$" + shopItems.get(i).getPrice() +
                    ", Emits: " + shopItems.get(i).getEmission() + "g/year CO\u2082" +
                    ", Outputs: " + shopItems.get(i).getOutput() + " kWh.");
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
