package worldofzuul;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
}