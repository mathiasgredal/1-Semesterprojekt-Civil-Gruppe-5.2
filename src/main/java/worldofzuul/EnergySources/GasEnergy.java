package worldofzuul.EnergySources;

public class GasEnergy extends EnergySource{
    private static String gasEnergyName = "Gas";
    private static int gasEnergyPrice = 18;
    private static int gasEnergyEmission =27;
    private static int gasEnergyOutput = 176;

    public GasEnergy() {
        super(gasEnergyName, gasEnergyPrice, gasEnergyEmission, gasEnergyOutput);
    }
}