package worldofzuul;

public class HydroEnergy extends EnergySource{
    private static String hydroEnergyName = "Hydro Energy";
    private static int hydroEnergyPrice = 20000;
    private static int hydroEnergyEmission = 0;
    private static int hydroEnergyOutput = 14;

    public HydroEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(hydroEnergyName, hydroEnergyPrice, hydroEnergyEmission, hydroEnergyOutput);
    }
}
