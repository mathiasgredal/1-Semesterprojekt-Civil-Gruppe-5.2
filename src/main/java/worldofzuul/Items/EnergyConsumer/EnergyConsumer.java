package worldofzuul.Items.EnergyConsumer;

import worldofzuul.Items.Buyable;

public abstract class EnergyConsumer implements Buyable {
    private String name;
    private double price;
    private double yearlyEmission;
    private double yearlyEnergyConsumption;
    private double yearlyCost;

    public EnergyConsumer(String name, double price, double yearlyEmission, double yearlyEnergyConsumption, double yearlyCost) {
        this.name = name;
        this.price = price;
        this.yearlyEmission = yearlyEmission;
        this.yearlyEnergyConsumption = yearlyEnergyConsumption;
        this.yearlyCost = yearlyCost;
    }

    public double getYearlyEmission() {
        return yearlyEmission;
    }

    public double getYearlyEnergyConsumption() {
        return yearlyEnergyConsumption;
    }

    public double getYearlyCost() {
        return yearlyCost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getInfo() {
        return name + ", price: " + price;
    }
}
