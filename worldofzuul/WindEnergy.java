package worldofzuul;

public class WindEnergy extends EnergySource{
    private static String windEnergyName = "Wind Energy";
    private static int windEnergyPrice = 1;
    private static int windEnergyEmission = 0;
    private static int windEnergyOutput = 100;

    public WindEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(windEnergyName, windEnergyPrice, windEnergyEmission, windEnergyOutput);
    }

}
