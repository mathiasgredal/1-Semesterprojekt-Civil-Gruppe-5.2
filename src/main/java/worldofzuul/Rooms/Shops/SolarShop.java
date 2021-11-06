package worldofzuul.Rooms.Shops;

import worldofzuul.EnergySources.SolarEnergy;

import java.util.List;

public class SolarShop extends Shop {
    public SolarShop() {
        super("at a solar shop, we sell solar panels", "solar shop");
        // TODO: Should we have multiple sizes of solar panels
        shopItems = List.of(new SolarEnergy());
    }
}
