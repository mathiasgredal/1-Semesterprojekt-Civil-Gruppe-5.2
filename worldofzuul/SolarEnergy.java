package worldofzuul;

public class SolarEnergy extends EnergySource{
    private static int solEnergyPrice = 3;
    private static int solEnergyEmission = 10;
    public SolarEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, solEnergyPrice, solEnergyEmission, energyOutput);
    }
}
