package worldofzuul.EnergySources;

public class HydroEnergy extends EnergySource {
    private static final String hydroEnergyName = "Hydro Energy";
    private static final int hydroEnergyPrice = 20000;
    private static final int hydroEnergyEmission = 0;
    private static final int hydroEnergyOutput = 14;

    public HydroEnergy() {
        super(hydroEnergyName, "Water turbine generator producing energy from the water", hydroEnergyPrice, hydroEnergyEmission, hydroEnergyOutput);
    }

}