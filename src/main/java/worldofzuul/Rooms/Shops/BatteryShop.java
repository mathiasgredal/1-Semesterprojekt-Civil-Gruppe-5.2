package worldofzuul.Rooms.Shops;

import worldofzuul.EnergySources.BatteryEnergy;
import worldofzuul.EnergySources.EnergySource;

import java.util.List;

public class BatteryShop extends Shop {
    public BatteryShop() {
        super("at a battery shop, we sell batteries", "battery shop");
        shopItems = List.of(new BatteryEnergy(EnergySource.EnergySourceSize.SMALL, 1000, 100),
                new BatteryEnergy(EnergySource.EnergySourceSize.MEDIUM, 5000, 800),
                new BatteryEnergy(EnergySource.EnergySourceSize.LARGE, 50000, 2000));
    }
}
