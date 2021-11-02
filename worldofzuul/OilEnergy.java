package worldofzuul;

public class OilEnergy extends EnergySource{
    private static String oilEnergyName = "Oil";
    private static int oilEnergyPrice = 13;
    private static int oilEnergyEmission = 31;
    private static int oilEnergyOutput = 151;

    public OilEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(oilEnergyName, oilEnergyPrice, oilEnergyEmission, oilEnergyOutput);
    }
}
