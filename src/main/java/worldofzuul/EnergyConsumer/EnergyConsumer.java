package worldofzuul.EnergyConsumer;

public abstract class EnergyConsumer {
    protected double yearlyEmission;
    protected double yearlyEnergyConsumption;
    protected double yearlyCost;

    public double getYearlyEmission() {
        return yearlyEmission;
    }

    public double getYearlyEnergyConsumption() {
        return yearlyEnergyConsumption;
    }

    public double getYearlyCost() {
        return yearlyCost;
    }
}
