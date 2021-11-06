package worldofzuul.EnergySources;

import worldofzuul.Game;

import java.util.Random;

public abstract class EnergySource {
    protected EnergySourceSize size;
    private String energyName;
    private final String energyDescription;
    private double energyPrice;
    private double energyEmission;
    private double energyOutput;
    private double totalGeneratedEnergy = 0;
    private double totalGeneratedMoney = 0;

    public EnergySource(String energyName, String energyDescription, double energyPrice, double energyEmission, double energyOutput) {
        this.energyName = energyName;
        this.energyDescription = energyDescription;
        this.size = EnergySourceSize.MEDIUM;
        this.energyPrice = energyPrice;
        this.energyEmission = energyEmission;
        this.energyOutput = energyOutput;
    }

    public EnergySource(String energyName, String energyDescription, EnergySourceSize size, double energyPrice, double energyEmission, double energyOutput) {
        this.energyName = energyName;
        this.energyDescription = energyDescription;
        this.size = size;
        this.energyPrice = energyPrice;
        this.energyEmission = energyEmission;
        this.energyOutput = energyOutput;
    }

    public String getEnergyName() {
        return energyName;
    }

    public void setEnergyName(String energyName) {
        this.energyName = energyName;
    }

    public double getEnergyPrice() {
        double newEnergyPrice = energyPrice;

        for (int i = 0; i < Game.instance.getGameYear(); i++) {
            if (energyEmission <= 0) {
                int decreasePercent = randomPercent(5, 2);
                newEnergyPrice -= decreasePercent;
            } else {
                int increasePercent = randomPercent(6, 1);
                newEnergyPrice += increasePercent;
            }
        }

        return (int) Math.round(newEnergyPrice);
    }

    public void setEnergyPrice(int energyPrice) {
        this.energyPrice = energyPrice;
    }

    public double getEnergyEmission() {
        return energyEmission;
    }

    public void setEnergyEmission(int energyEmission) {
        this.energyEmission = energyEmission;
    }

    public double getEnergyOutput() {
        return energyOutput;
    }

    public void setEnergyOutput(int energyOutput) {
        this.energyOutput = energyOutput;
    }

    public void addYearlyEnergyProduction(double electricityPrice) {
        totalGeneratedEnergy += energyOutput;
        totalGeneratedMoney += energyOutput * electricityPrice;
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
        return !(energyEmission > 0);
    }

    public boolean isFossil() {
        return energyEmission > 0;
    }

    public void printDescription() {
        System.out.println(energyDescription);
    }

    public int randomPercent(int max, int min) {
        Random rand = new Random();
        double random_int = rand.nextInt(max - min + 1) + 2.0 / 100.0;
        double procent = (random_int / 100);
        return (int) Math.round(energyPrice * procent);
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
