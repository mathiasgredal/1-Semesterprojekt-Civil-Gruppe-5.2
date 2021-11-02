package worldofzuul;

public class HydroEnergy extends EnergySource{
    private static String hydroEnergyName = "Hydro Energy";
    private static int hydroEnergyPrice = 22;
    private static int hydroEnergyEmission = 2;
    private static int hydroEnergyOutput = 99;

    public HydroEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(hydroEnergyName, hydroEnergyPrice, hydroEnergyEmission, hydroEnergyOutput);
    }
}
