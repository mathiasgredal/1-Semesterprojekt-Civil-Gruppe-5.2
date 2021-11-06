package worldofzuul.Rooms.Shops;

import worldofzuul.EnergySources.*;

import java.util.List;

public class WindShop extends Shop {
    public WindShop() {
        super("at a wind shop, we sell wind turbines", "windturbine shop");
        shopItems = List.of(new WindEnergy());
    }
}
