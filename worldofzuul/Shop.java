package worldofzuul;

public class Shop extends Room{
    private EnergySource[] shopItems;

    public Shop(String description, EnergySource[] shopItems) {
        super(description);
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
            System.out.println("[" + (i+1)  + "] " + shopItems[i].getEnergyName() + ", Costs: " + "$" + shopItems[i].getEnergyPrice() + ", Emits: " + shopItems[i].getEnergyEmission() + "g CO\u2082" + ", Outputs: " + shopItems[i].getEnergyOutput() + " kWh.");
        }
    }
}
