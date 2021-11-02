package worldofzuul;

public class OilEnergy extends EnergySource{
    private static int oilEnergyPrice = 23;

    public OilEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, oilEnergyPrice, energyEmission, energyOutput);
    }
}
