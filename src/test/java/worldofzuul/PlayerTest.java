package worldofzuul;

import org.junit.jupiter.api.Test;
import worldofzuul.Items.EnergySource;
import worldofzuul.Items.EnergySourceSize;
import worldofzuul.Rooms.Shops.EnergyShop;
import worldofzuul.Rooms.Shops.Shop;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void getPlayerEconomy() {
        Player player = new Player(500);
        assertEquals(player.getPlayerEconomy(), 500);
    }

    @Test
    void withdrawMoney() {
        Player player = new Player(500);

        // Should return false when overdrawing
        assertFalse(player.withdrawMoney(1000));

        // Should return true when drawing allowed amount
        assertTrue(player.withdrawMoney(1));

        // Should have updated balance after withdrawing
        assertEquals(player.getPlayerEconomy(), 499);
    }

    @Test
    void insertMoney() {
        Player player = new Player(500);
        player.insertMoney(1000);
        assertEquals(player.getPlayerEconomy(), 1500);
    }

    @Test
    void getShopDetails() {
        var myEnergy = new EnergySource(
                "My energy",
                "cold fusion", EnergySourceSize.MEDIUM,
<<<<<<< HEAD
                1000, 24, 1000, 0, "", 1, 1);
=======
                1000, 24, 1000);
>>>>>>> parent of 7524c53 (Merge branch 'master' into add-gui-functionality)

        Shop shop = new EnergyShop("Magic shop",
                "A shop test", List.of(myEnergy));

        assertEquals("A shop test", shop.getShortDescription());
        assertEquals(24, ((EnergySource) shop.getShopItem(0)).getEmission());
    }

    @Test
    void getGameYear() {

        assertEquals(0, Game.instance.getGameYear());
    }
}