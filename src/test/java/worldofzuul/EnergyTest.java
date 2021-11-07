package worldofzuul;

import org.junit.jupiter.api.Test;
import worldofzuul.EnergySources.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyTest {
    GasEnergy gasEnergy = new GasEnergy();
    CoalEnergy coalEnergy = new CoalEnergy();
    OilEnergy oilEnergy = new OilEnergy();
    WindEnergy windEnergy = new WindEnergy();

    @Test
    void setGasEnergyName() {
        gasEnergy.setName("Not a gas");
        assertEquals("Not a gas", gasEnergy.getName());
    }

    @Test
    void setGasEnergyPrice() {
        gasEnergy.setPrice(999);
        assertEquals(999, gasEnergy.getPrice());
    }

    @Test
    void setGasEnergyEmission() {
        gasEnergy.setEmission(1010);
        assertEquals(1010, gasEnergy.getEmission());
    }

    @Test
    void setGasEnergyOutput() {
        gasEnergy.setOutput(73);
        assertEquals(73, gasEnergy.getOutput());
    }

    //Gas energy tests

    @Test
    void getGasEnergyName() {
        assertEquals("Gas", gasEnergy.getName());
    }

    @Test
    void getGasEnergyPrice() {
        assertEquals(18, gasEnergy.getPrice());
    }

    @Test
    void getGasEnergyEmission() {
        assertEquals(27, gasEnergy.getEmission());
    }

    @Test
    void getGasEnergyOutput() {
        assertEquals(176, gasEnergy.getOutput());
    }

    //Coal energy tests

    @Test
    void getCoalEnergyName() {
        assertEquals("Coal", coalEnergy.getName());
    }

    @Test
    void getCoalEnergyPrice() {
        assertEquals(13, coalEnergy.getPrice());
    }

    @Test
    void getCoalEnergyEmission() {
        assertEquals(24, coalEnergy.getEmission());
    }

    @Test
    void getCoalEnergyOutput() {
        assertEquals(90, coalEnergy.getOutput());
    }

    //Oil energy tests

    @Test
    void getOilEnergyName() {
        assertEquals("Oil", oilEnergy.getName());
    }

    @Test
    void getOilEnergyPrice() {
        assertEquals(13, oilEnergy.getPrice());
    }

    @Test
    void getOilEnergyEmission() {
        assertEquals(31, oilEnergy.getEmission());
    }

    @Test
    void getOilEnergyOutput() {
        assertEquals(151, oilEnergy.getOutput());
    }

    //Wind energy tests

    @Test
    void getWindEnergyName() {
        assertEquals("Wind Energy", windEnergy.getName());
    }

    @Test
    void getWindEnergyPrice() {
        assertEquals(163460, windEnergy.getPrice());
    }

    @Test
    void getWindEnergyEmission() {
        assertEquals(0, windEnergy.getEmission());
    }

    @Test
    void getWindEnergyOutput() {
        assertEquals(20, windEnergy.getOutput());
    }

}

