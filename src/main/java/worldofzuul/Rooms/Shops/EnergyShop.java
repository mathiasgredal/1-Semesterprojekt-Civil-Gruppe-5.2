package worldofzuul.Rooms.Shops;

import worldofzuul.Items.Buyable;
import worldofzuul.Items.EnergySource;

import java.util.List;

/**
 * The energyshop is used to sell items of the class EnergySource
 */
public class EnergyShop extends Shop {

    /**
     * Construct shop with list of shop items
     *
     * @param name        Room name, used by the config file to insert shop items later
     * @param description Room description
     * @param shopItems   List of buyable shop items
     */
    public EnergyShop(String name, String description, List<Buyable> shopItems) {
        super(name, description, shopItems);
    }

    /**
     * Initialize shop with no shop items
     *
     * @param name        Room name, used by the config file to insert shop items later
     * @param description Room description
     */
    public EnergyShop(String name, String description) {
        super(name, description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnergySource getShopItem(int index) {
        return (EnergySource) super.getShopItem(index);
    }
}
