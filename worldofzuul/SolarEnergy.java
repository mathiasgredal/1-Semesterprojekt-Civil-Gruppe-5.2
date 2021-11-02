package worldofzuul;

public class SolarEnergy extends EnergySource{
    private static String solEnergyName = "Sol Energy";
    private static int solEnergyPrice = 3;
    private static int solEnergyEmission = 10;
    private static int solEnergyOutput = 101;

    public SolarEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(solEnergyName, solEnergyPrice, solEnergyEmission, solEnergyOutput);
    }
}
