package worldofzuul;

public class SolarEnergy extends EnergySource{
    private static String solarEnergyName = "Sol Energy";
    private static int solarEnergyPrice = 30900;
    private static int solarEnergyEmission = 0;
    private static int solarEnergyOutput = 1;

    public SolarEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(solarEnergyName, solarEnergyPrice, solarEnergyEmission, solarEnergyOutput);
    }
}
