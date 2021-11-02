package worldofzuul;

public class WindEnergy extends EnergySource{
    private static int windEnergyPrice = 1;
    
    public WindEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(energyName, windEnergyPrice, energyEmission, energyOutput);
    }
    
}
