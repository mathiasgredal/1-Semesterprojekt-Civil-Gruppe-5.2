package worldofzuul;

public class SolarEnergy extends EnergySource{
    private static String solarEnergyName = "Sol Energy";
    private static int solarEnergyPrice = 3;
    private static int solarEnergyEmission = 10;
    private static int solarEnergyOutput = 101;

    public SolarEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(solarEnergyName, solarEnergyPrice, solarEnergyEmission, solarEnergyOutput);
    }
}
