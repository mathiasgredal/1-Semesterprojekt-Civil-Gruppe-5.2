package worldofzuul;

public class SolarEnergy extends EnergySource{
    private static int solEnergyPrice = 3;

    public SolarEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, solEnergyPrice, energyEmission, energyOutput);
    }
}
