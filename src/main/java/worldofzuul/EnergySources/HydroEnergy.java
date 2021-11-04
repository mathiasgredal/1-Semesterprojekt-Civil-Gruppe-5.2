package worldofzuul.EnergySources;

public class HydroEnergy extends EnergySource {
    private static String hydroEnergyName = "Hydro Energy";
    private static int hydroEnergyPrice = 20000;
    private static int hydroEnergyEmission = 0;
    private static int hydroEnergyOutput = 14;

    public HydroEnergy() {
        super(hydroEnergyName, "Water turbine generator producing energy from the water", hydroEnergyPrice, hydroEnergyEmission, hydroEnergyOutput);
    }

}