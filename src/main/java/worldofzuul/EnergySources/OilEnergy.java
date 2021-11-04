package worldofzuul.EnergySources;

public class OilEnergy extends EnergySource {
    private static String oilEnergyName = "Oil";
    private static int oilEnergyPrice = 13;
    private static int oilEnergyEmission = 31;
    private static int oilEnergyOutput = 151;

    public OilEnergy() {
        super(oilEnergyName, "It is energy from oil", oilEnergyPrice, oilEnergyEmission, oilEnergyOutput);
    }

}
