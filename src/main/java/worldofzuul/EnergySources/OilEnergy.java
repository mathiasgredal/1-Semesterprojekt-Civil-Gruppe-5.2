package worldofzuul.EnergySources;

public class OilEnergy extends EnergySource {
    private static final String oilEnergyName = "Oil";
    private static final int oilEnergyPrice = 13;
    private static final int oilEnergyEmission = 31;
    private static final int oilEnergyOutput = 151;

    public OilEnergy() {
        super(oilEnergyName, "It is energy from oil", oilEnergyPrice, oilEnergyEmission, oilEnergyOutput);
    }

}
