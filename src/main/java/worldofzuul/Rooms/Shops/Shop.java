package worldofzuul.Rooms.Shops;

import worldofzuul.EnergySources.*;
import worldofzuul.Game;
import worldofzuul.Rooms.Room;

import java.util.List;

public abstract class Shop extends Room {
    protected List<EnergySource> shopItems;

    public Shop(String description, String name) {
        super(description, name);
    }

    public EnergySource getShopItem(int index) {
        return shopItems.get(index);
    }

    public List<EnergySource> getShopItems() {
        return shopItems;
    }

    public void printShopDetails(double availableMoney) {
        for (int i = 0; i < shopItems.size(); i++) {
            System.out.print(availableMoney > shopItems.get(i).getEnergyPrice() ? "\u001B[32m" : "\u001B[31m");
            System.out.println("[" + (i + 1) + "] " +
                    shopItems.get(i).getEnergyName() +
                    ", Costs: " + "$" + shopItems.get(i).getEnergyPrice() +
                    ", Emits: " + shopItems.get(i).getEnergyEmission() + "g/year CO\u2082" +
                    ", Outputs: " + shopItems.get(i).getEnergyOutput() + " kWh.");
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
