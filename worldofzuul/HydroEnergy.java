package worldofzuul;

public class HydroEnergy extends EnergySource{
    private static int hydroEnergyPrice = 22;

    public HydroEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, hydroEnergyPrice, energyEmission, energyOutput);
    }
}
