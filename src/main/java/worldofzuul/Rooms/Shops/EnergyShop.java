package worldofzuul.Rooms.Shops;

import worldofzuul.Items.Buyable;
import worldofzuul.Items.EnergySource;

import java.util.List;

public class EnergyShop extends Shop {
    public EnergyShop(String name, String description, List<Buyable> shopItems) {
        super(name, description, shopItems);
    }

    public EnergyShop(String name, String description) {
        super(name, description);
    }

    public EnergySource getShopItem(int index) {
        return (EnergySource) super.getShopItem(index);
    }
}
