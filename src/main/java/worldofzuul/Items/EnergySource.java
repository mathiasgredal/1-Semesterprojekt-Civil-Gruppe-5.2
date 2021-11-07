package worldofzuul.Items;

import worldofzuul.Game;

import java.util.Random;

public class EnergySource implements Buyable {
    private String name;
    private String description;
    private EnergySourceSize size;
    private double price;
    private double emission;
    private double output = 0;
    private double capacity = 0;

    private double totalGeneratedEnergy = 0;
    private double totalGeneratedMoney = 0;

    public EnergySource() {
    }

    public EnergySource(String energyName, String energyDescription, double energyPrice, double energyEmission, double energyOutput) {
        this.name = energyName;
        this.description = energyDescription;
        this.size = EnergySourceSize.MEDIUM;
        this.price = energyPrice;
        this.emission = energyEmission;
        this.output = energyOutput;
    }

    public EnergySource(String energyName, String energyDescription, EnergySourceSize size, double energyPrice, double energyEmission, double energyOutput) {
        this.name = energyName;
        this.description = energyDescription;
        this.size = size;
        this.price = energyPrice;
        this.emission = energyEmission;
        this.output = energyOutput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        double newEnergyPrice = price;

        for (int i = 0; i < Game.instance.getGameYear(); i++) {
            if (emission <= 0) {
                int decreasePercent = randomPercent(5, 2);
                newEnergyPrice -= decreasePercent;
            } else {
                int increasePercent = randomPercent(6, 1);
                newEnergyPrice += increasePercent;
            }
        }

        return newEnergyPrice;
    }

    @Override
    public String getInfo() {
        return getName() +
                ", Costs: " + "$" + getPrice() +
                ", Emits: " + getEmission() + "g/year CO\u2082" +
                ", Outputs: " + getOutput() + " kWh.";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEmission() {
        return emission;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public void addYearlyEnergyProduction(double electricityPrice) {
        totalGeneratedEnergy += output;
        totalGeneratedMoney += output * electricityPrice;
    }

    public double getTotalGeneratedEnergy() {
        return totalGeneratedEnergy;
    }

    public double getTotalGeneratedMoney() {
        return totalGeneratedMoney;
    }

    public EnergySourceSize getSize() {
        return size;
    }

    public boolean isRenewable() {
        return !(emission > 0);
    }

    public boolean isFossil() {
        return emission > 0;
    }

    public void printDescription() {
        System.out.println(description);
    }

    public int randomPercent(int max, int min) {
        Random rand = new Random();
        double random_int = rand.nextInt(max - min + 1) + 2.0 / 100.0;
        double procent = (random_int / 100);
        return (int) Math.round(price * procent);
    }

    public void setSize(EnergySourceSize size) {
        this.size = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public boolean isBattery() {
        return capacity > 0;
    }

    public enum EnergySourceSize {
        SMALL("small"), MEDIUM("medium"), LARGE("large");
        private final String name;

        EnergySourceSize(String name) {
            this.name = name;
        }

        public boolean equalsName(String other) {
            return name.equals(other);
        }

        public String toString() {
            return this.name;
        }

        public String upperCaseName() {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
    }
}
