package worldofzuul;

import org.junit.jupiter.api.Test;
import worldofzuul.Items.EnergySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyTest {
    EnergySource energy = new EnergySource();

    @Test
    void setEnergyName() {
        energy.setName("Not a gas");
        assertEquals("Not a gas", energy.getName());
    }

    @Test
    void setEnergyPrice() {
        energy.setPrice(999);
        assertEquals(999, energy.getPrice());
    }

    @Test
    void setEnergyEmission() {
        energy.setEmission(1010);
        assertEquals(1010, energy.getEmission());
    }

    @Test
    void setEnergyOutput() {
        energy.setOutput(73);
        assertEquals(73, energy.getOutput());
    }
}

