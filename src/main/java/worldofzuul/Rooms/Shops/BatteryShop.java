package worldofzuul.Rooms.Shops;

import worldofzuul.EnergySources.*;

import java.util.List;

public class BatteryShop extends Shop {
    public BatteryShop() {
        super("at a battery shop, we sell batteries");
        shopItems = List.of(new BatteryEnergy(EnergySource.EnergySourceSize.SMALL, 1000, 100),
                new BatteryEnergy(EnergySource.EnergySourceSize.MEDIUM, 5000, 800),
                new BatteryEnergy(EnergySource.EnergySourceSize.LARGE, 50000, 2000));
    }
}
