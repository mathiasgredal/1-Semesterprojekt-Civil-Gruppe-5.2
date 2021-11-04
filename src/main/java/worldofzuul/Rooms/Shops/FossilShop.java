package worldofzuul.Rooms.Shops;

import worldofzuul.EnergySources.CoalEnergy;
import worldofzuul.EnergySources.EnergySource;
import worldofzuul.EnergySources.GasEnergy;
import worldofzuul.EnergySources.OilEnergy;

import java.util.List;

public class FossilShop extends Shop{

    public FossilShop() {
        super("at a fossil energy provider, we provide energy from fossil fuels");
        shopItems = List.of(new CoalEnergy(), new OilEnergy(), new GasEnergy());
    }
}
