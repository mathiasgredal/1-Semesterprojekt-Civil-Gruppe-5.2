package worldofzuul;

public class CoalEnergy extends EnergySource{
    private static int coalEnergyPrice = 20;

    public CoalEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, coalEnergyPrice, energyEmission, energyOutput);
    }
}
