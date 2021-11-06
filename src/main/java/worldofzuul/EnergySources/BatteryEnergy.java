package worldofzuul.EnergySources;

/**
 * A battery isn't exactly an EnergySource, so this abstraction doens't fit perfectly
 */
public class BatteryEnergy extends EnergySource {
    private final double energyStorage;

    public BatteryEnergy(EnergySourceSize size, double energyPrice, double energyStorage) {
        super("Battery",
                "Battery to save excess energy from renewables and use it later when it is needed more",
                size, energyPrice, 0, 0);
        this.energyStorage = energyStorage;
    }

    public double getEnergyStorage() {
        return energyStorage;
    }
}
