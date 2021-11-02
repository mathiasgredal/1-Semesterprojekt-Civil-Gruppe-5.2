package worldofzuul;

public class OilEnergy extends EnergySource{
    private static String oilEnergyName = "Oil";
    private static int oilEnergyPrice = 23;
    private static int oilEnergyEmission = 20;
    private static int oilEnergyOutput = 30;

    public OilEnergy(String energyName, int energyPrice, int energyEmission, int energyOutput) {
        super(oilEnergyName, oilEnergyPrice, oilEnergyEmission, oilEnergyOutput);
    }
}
