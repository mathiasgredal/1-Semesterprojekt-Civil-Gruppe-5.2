package worldofzuul.EnergySources;

public class CoalEnergy extends EnergySource {
    private static String coalEnergyName = "Coal";
    private static int coalEnergyPrice = 13;
    private static int coalEnergyEmission = 24;
    private static int coalEnergyOutput = 90;

    public CoalEnergy() {
        super(coalEnergyName, "It is energy from coal", coalEnergyPrice, coalEnergyEmission, coalEnergyOutput);
    }
}