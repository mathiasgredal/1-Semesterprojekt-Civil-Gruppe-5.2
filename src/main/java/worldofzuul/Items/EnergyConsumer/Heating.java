package worldofzuul.Items.EnergyConsumer;

public abstract class Heating extends EnergyConsumer {
    /**
     * Abstract Heating class constructor which inherit its constructor from the EnergyConsumer class
     */
    public Heating(String name, double price, double yearlyEmission, double yearlyEnergyConsumption, double yearlyCost) {
        super(name, price, yearlyEmission, yearlyEnergyConsumption, yearlyCost);
    }
}
