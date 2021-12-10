package worldofzuul.Items.EnergyConsumer;

public abstract class Car extends EnergyConsumer {
    /**
     * Abstract car class constructor which inherit its constructor from the EnergyConsumer class
     */
    public Car(String name, double price, double yearlyEmission, double yearlyEnergyConsumption, double yearlyCost) {
        super(name, price, yearlyEmission, yearlyEnergyConsumption, yearlyCost);
    }
}
