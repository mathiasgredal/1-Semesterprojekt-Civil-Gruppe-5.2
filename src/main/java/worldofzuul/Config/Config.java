package worldofzuul.Config;

import worldofzuul.EnergySources.EnergySource;

import java.util.Map;

/**
 * Class for storing program configuration loaded in from YAML config file.
 */
public class Config {
    /**
     * A map for storing the buyable shop items for each store.
     * Note: Due to type-erasure, we have to use an array instead of an arraylist,
     * so that SnakeYAML deserialise the data to EnergySource instead of LinkedHashMap.
     * Source: https://stackoverflow.com/a/40465555
     * Time wasted: ~3 hours
     */
    private Map<String, EnergySource[]> shopItems;

    /**
     * A no-arg constructor for the java bean pattern
     */
    Config() {
    }

    /**
     * Getter for shop items
     *
     * @return A map with a key indicating the shop name
     */
    public Map<String, EnergySource[]> getShopItems() {
        return shopItems;
    }

    /**
     * A setter for shop items
     *
     * @param shopItems A map for
     */
    public void setShopItems(Map<String, EnergySource[]> shopItems) {
        this.shopItems = shopItems;
    }
}