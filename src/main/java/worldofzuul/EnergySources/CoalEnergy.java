package worldofzuul.EnergySources;

public class CoalEnergy extends EnergySource {
    private static final String coalEnergyName = "Coal";
    private static final int coalEnergyPrice = 13;
    private static final int coalEnergyEmission = 24;
    private static final int coalEnergyOutput = 90;

    public CoalEnergy() {
        super(coalEnergyName, "It is energy from coal", coalEnergyPrice, coalEnergyEmission, coalEnergyOutput);
    }
}