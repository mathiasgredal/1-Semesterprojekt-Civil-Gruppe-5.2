package worldofzuul.Rooms;

import worldofzuul.EnergySources.*;
import worldofzuul.Game;

public class Shop extends Room{
    private EnergySource[] shopItems;

    public Shop(String description, String name, EnergySource[] shopItems) {
        super(description, name);
        this.shopItems = shopItems;
    }

    public EnergySource getShopItem(int index) {
        return shopItems[index];
    }

    public EnergySource[] getShopItems() {
        return shopItems;
    }

    public void setShopItems(EnergySource[] shopItems) {
        this.shopItems = shopItems;
    }

    public void printShopDetails(){
        for(int i = 0; i < shopItems.length; i++){
            System.out.println("[" + (i+1)  + "] " + shopItems[i].getEnergyName() + ", Costs: " + "$" + shopItems[i].getEnergyPrice() + ", Emits: " + shopItems[i].getEnergyEmission() + "g/year CO\u2082" + ", Outputs: " + shopItems[i].getEnergyOutput() + " kWh.");
        }
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());
        System.out.println("Your available balance: "+ game.getPlayer().getPlayerEconomy()+"Kr.");
        //Lists the available items, that was added in createRoom
        System.out.println("\nAvailable items: ");
        printShopDetails();

        //Gets the exits out of the room.
        System.out.println("\n" + getExitString());
    }
}
