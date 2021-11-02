package worldofzuul.EnergySources;

public class CoalEnergy extends EnergySource{
    private static String coalEnergyName = "Coal";
    private static int coalEnergyPrice = 13;
    private static int coalEnergyEmission = 24;
    private static int coalEnergyOutput = 90;

    public CoalEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(coalEnergyName, coalEnergyPrice, coalEnergyEmission, coalEnergyOutput);
    }
}