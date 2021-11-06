package worldofzuul.EnergySources;

public class GasEnergy extends EnergySource {
    private static final String gasEnergyName = "Gas";
    private static final int gasEnergyPrice = 18;
    private static final int gasEnergyEmission = 27;
    private static final int gasEnergyOutput = 176;

    public GasEnergy() {
        super(gasEnergyName, "It is energy from natural gas", gasEnergyPrice, gasEnergyEmission, gasEnergyOutput);
    }

}