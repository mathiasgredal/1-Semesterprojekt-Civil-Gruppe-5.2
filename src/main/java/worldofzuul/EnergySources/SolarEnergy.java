package worldofzuul.EnergySources;

public class SolarEnergy extends EnergySource{
    private static String solarEnergyName = "Solar Energy";
    private static int solarEnergyPrice = 30900;
    private static int solarEnergyEmission = 0;
    private static int solarEnergyOutput = 1;

    public SolarEnergy() {
        super(solarEnergyName, solarEnergyPrice, solarEnergyEmission, solarEnergyOutput);
    }
}