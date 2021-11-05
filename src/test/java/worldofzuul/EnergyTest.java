package worldofzuul;

import org.junit.jupiter.api.Test;
import worldofzuul.EnergySources.*;
import worldofzuul.Rooms.Shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyTest {
    GasEnergy gasEnergy = new GasEnergy();
    CoalEnergy coalEnergy = new CoalEnergy();
    OilEnergy oilEnergy = new OilEnergy();
    WindEnergy windEnergy = new WindEnergy();

    @Test
    void setGasEnergyName(){
        gasEnergy.setEnergyName("Not a gas");
        assertEquals("Not a gas", gasEnergy.getEnergyName());
    }

    @Test
    void setGasEnergyPrice(){
        gasEnergy.setEnergyPrice(999);
        assertEquals(999, gasEnergy.getEnergyPrice());
    }

    @Test
    void setGasEnergyEmission(){
        gasEnergy.setEnergyEmission(1010);
        assertEquals(1010, gasEnergy.getEnergyEmission());
    }

    @Test
    void setGasEnergyOutput(){
        gasEnergy.setEnergyOutput(73);
        assertEquals(73, gasEnergy.getEnergyOutput());
    }

    //Gas energy tests

    @Test
    void getGasEnergyName(){ assertEquals("Gas", gasEnergy.getEnergyName()); }

    @Test
    void getGasEnergyPrice(){ assertEquals(18, gasEnergy.getEnergyPrice());}

    @Test
    void getGasEnergyEmission(){ assertEquals(27, gasEnergy.getEnergyEmission());}

    @Test
    void getGasEnergyOutput(){ assertEquals(176, gasEnergy.getEnergyOutput());}

    //Coal energy tests

    @Test
    void getCoalEnergyName(){ assertEquals("Coal", coalEnergy.getEnergyName()); }

    @Test
    void getCoalEnergyPrice(){ assertEquals(13, coalEnergy.getEnergyPrice());}

    @Test
    void getCoalEnergyEmission(){ assertEquals(24, coalEnergy.getEnergyEmission());}

    @Test
    void getCoalEnergyOutput(){ assertEquals(90, coalEnergy.getEnergyOutput());}

    //Oil energy tests

    @Test
    void getOilEnergyName(){ assertEquals("Oil", oilEnergy.getEnergyName()); }

    @Test
    void getOilEnergyPrice(){ assertEquals(13, oilEnergy.getEnergyPrice());}

    @Test
    void getOilEnergyEmission(){ assertEquals(31, oilEnergy.getEnergyEmission());}

    @Test
    void getOilEnergyOutput(){ assertEquals(151, oilEnergy.getEnergyOutput());}

    //Wind energy tests

    @Test
    void getWindEnergyName(){ assertEquals("Wind Energy", windEnergy.getEnergyName()); }

    @Test
    void getWindEnergyPrice(){ assertEquals(163460, windEnergy.getEnergyPrice());}

    @Test
    void getWindEnergyEmission(){ assertEquals(0, windEnergy.getEnergyEmission());}

    @Test
    void getWindEnergyOutput(){ assertEquals(20, windEnergy.getEnergyOutput());}

}

