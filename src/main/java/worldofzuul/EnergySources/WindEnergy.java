package worldofzuul.EnergySources;

public class WindEnergy extends EnergySource {
    private static final String windEnergyName = "Wind Energy";
    private static final int windEnergyPrice = 163460;
    private static final int windEnergyEmission = 0;
    private static final int windEnergyOutput = 20;

    public WindEnergy() {
        super(windEnergyName, "Wind turbine producing energy from the wind", windEnergyPrice, windEnergyEmission, windEnergyOutput);
    }

}