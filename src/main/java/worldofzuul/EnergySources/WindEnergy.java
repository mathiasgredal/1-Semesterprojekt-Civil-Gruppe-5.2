package worldofzuul.EnergySources;

public class WindEnergy extends EnergySource {
    private static String windEnergyName = "Wind Energy";
    private static int windEnergyPrice = 163460;
    private static int windEnergyEmission = 0;
    private static int windEnergyOutput = 20;

    public WindEnergy() {
        super(windEnergyName, "Wind turbine producing energy from the wind", windEnergyPrice, windEnergyEmission, windEnergyOutput);
    }

}