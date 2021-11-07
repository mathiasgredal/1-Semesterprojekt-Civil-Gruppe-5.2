package worldofzuul.Items.EnergyConsumer;

public abstract class Heating extends EnergyConsumer {
    public Heating(String name, double price, double yearlyEmission, double yearlyEnergyConsumption, double yearlyCost) {
        super(name, price, yearlyEmission, yearlyEnergyConsumption, yearlyCost);
    }
}
