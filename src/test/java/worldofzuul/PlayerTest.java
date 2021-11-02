package worldofzuul;

import org.junit.jupiter.api.Test;
import worldofzuul.EnergySources.CoalEnergy;
import worldofzuul.EnergySources.EnergySource;
import worldofzuul.EnergySources.GasEnergy;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Input.Parser;
import worldofzuul.Rooms.Shop;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    int moneyAmount = 500;
    Player player = new Player(moneyAmount, new ArrayList<>());

    @Test
    void getPlayerEconomy() {
        assertEquals(player.getPlayerEconomy(), moneyAmount);
    }

    @Test
    void setPlayerEconomy() {
        moneyAmount = 1000;
        player.setPlayerEconomy(moneyAmount);
        assertEquals(player.getPlayerEconomy(), moneyAmount);
    }

   @Test
    void getShopDetails(){
        Shop shop = new Shop("A shop test", new EnergySource[]{new CoalEnergy()});

        assertEquals("A shop test", shop.getShortDescription());
        assertEquals(24 ,shop.getShopItem(0).getEnergyEmission());
    }

    @Test
    void getGameYear(){
        Game game = new Game();

        assertEquals(0, game.getGameYear());
    }
}