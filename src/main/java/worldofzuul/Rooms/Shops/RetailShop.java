package worldofzuul.Rooms.Shops;

import worldofzuul.Items.Buyable;

import java.util.List;

public class RetailShop extends Shop {
    public RetailShop(String name, String description, List<Buyable> shopItems) {
        super(name, description, shopItems);
    }

    public RetailShop(String name, String description) {
        super(name, description);
    }
}
