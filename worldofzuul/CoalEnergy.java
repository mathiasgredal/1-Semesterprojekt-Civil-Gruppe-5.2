package worldofzuul;

public class CoalEnergy extends EnergySource{
    private static String coalEnergyName = "Coal";
    private static int coalEnergyPrice = 20;
    private static int coalEnergyEmission = 30;
    private static int coalEnergyOutput = 20;

    public CoalEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(coalEnergyName, coalEnergyPrice, coalEnergyEmission, coalEnergyOutput);
    }
}
