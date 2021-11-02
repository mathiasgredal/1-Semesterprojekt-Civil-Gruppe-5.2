package worldofzuul;

public class WindEnergy extends EnergySource{
    private static int windEnergyPrice = 1;
    private static int windEnergyEmission = 0;

    public WindEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, windEnergyPrice, windEnergyEmission, energyOutput);
    }

}
