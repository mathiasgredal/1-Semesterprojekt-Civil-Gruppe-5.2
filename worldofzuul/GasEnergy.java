package worldofzuul;

public class GasEnergy extends EnergySource{
    private static int gasEnergyPrice = 21;

    public GasEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, gasEnergyPrice, energyEmission, energyOutput);
    }
}
