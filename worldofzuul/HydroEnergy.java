package worldofzuul;

public class HydroEnergy extends EnergySource{
    private static int hydroEnergyPrice = 22;
    private static int hydroEnergyEmission = 2;

    public HydroEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, hydroEnergyPrice, hydroEnergyEmission, energyOutput);
    }
}
