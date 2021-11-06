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
//    int moneyAmount = 500;
//    Player player = new Player(moneyAmount);
//    GasEnergy gasEnergy = new GasEnergy();
//    CoalEnergy coalEnergy = new CoalEnergy();
//
//    @Test
//    void getPlayerEconomy() {
//        assertEquals(player.getPlayerEconomy(), moneyAmount);
//    }
//
//    @Test
//    void setPlayerEconomy() {
//        moneyAmount = 1000;
//        player.setPlayerEconomy(moneyAmount);
//        assertEquals(player.getPlayerEconomy(), moneyAmount);
//    }
//
//    @Test
//    void getGasEnergyName(){ assertEquals("Gas", gasEnergy.getEnergyName()); }
//
//    @Test
//    void getGasEnergyPrice(){ assertEquals(18, gasEnergy.getEnergyPrice());}
//
//    @Test
//    void getCoalEnergyName(){ assertEquals("Coal", coalEnergy.getEnergyName()); }
//
//    @Test
//    void getCoalEnergyPrice(){ assertEquals(13, coalEnergy.getEnergyPrice());}
//
//    @Test
//    void testCommandClass(){
//        CommandWord commandWord = CommandWord.GO;
//        String secondWord = "east";
//
//        Command command = new Command(commandWord, secondWord);
//
//        if(command.hasSecondWord()){
//           assertEquals(CommandWord.GO, commandWord);
//           assertEquals("east", secondWord);
//        }
//    }
//
//    @Test
//    void hasSecondWordTest(){
//        Command command = new Command(CommandWord.BUY, null);
//
//        if(command.hasSecondWord()){
//            assertEquals(false, command.hasSecondWord());
//        }
//    }
//
//   @Test
//    void getShopDetails(){
//        Shop shop = new Shop("A shop test", new EnergySource[]{new CoalEnergy()});
//
//        assertEquals("A shop test", shop.getShortDescription());
//        assertEquals(24 ,shop.getShopItem(0).getEnergyEmission());
//    }

    @Test
    void getGameYear(){
        Game game = new Game();

        assertEquals(0, game.getGameYear());
    }
}