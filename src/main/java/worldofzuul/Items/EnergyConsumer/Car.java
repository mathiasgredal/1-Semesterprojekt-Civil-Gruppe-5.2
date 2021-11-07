package worldofzuul.Items.EnergyConsumer;

public abstract class Car extends EnergyConsumer {
    public Car(String name, double price, double yearlyEmission, double yearlyEnergyConsumption, double yearlyCost) {
        super(name, price, yearlyEmission, yearlyEnergyConsumption, yearlyCost);
    }
}
