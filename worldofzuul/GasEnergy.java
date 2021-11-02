package worldofzuul;

public class GasEnergy extends EnergySource{
    private static String gasEnergyName = "Gas";
    private static int gasEnergyPrice = 21;
    private static int gasEnergyEmission =30;
    private static int gasEnergyOutput = 20;

    public GasEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(gasEnergyName, gasEnergyPrice, gasEnergyEmission, gasEnergyOutput);
    }
}
