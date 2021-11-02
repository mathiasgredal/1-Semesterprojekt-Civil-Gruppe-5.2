package worldofzuul.EnergySources;

public class WindEnergy extends EnergySource{
    private static String windEnergyName = "Wind Energy";
    private static int windEnergyPrice = 163460;
    private static int windEnergyEmission = 0;
    private static int windEnergyOutput = 20;

    public WindEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(windEnergyName, windEnergyPrice, windEnergyEmission, windEnergyOutput);
    }

}