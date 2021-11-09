package worldofzuul.Rooms.Shops;

import worldofzuul.Items.Buyable;

import java.util.List;

/**
 * The retail shop is used to sell energy consumers
 * NOTE: Currently this class doesn't do anything other than the Shop class,
 * but since it is abstract we need this for the retail shop
 */
public class RetailShop extends Shop {
    /**
     * {@inheritDoc}
     */
    public RetailShop(String name, String description, List<Buyable> shopItems) {
        super(name, description, shopItems);
    }

    /**
     * {@inheritDoc}
     */
    public RetailShop(String name, String description) {
        super(name, description);
    }
}
