package worldofzuul.EnergySources;

import worldofzuul.Game;
import worldofzuul.Rooms.Shop;

import java.util.ArrayList;
import java.util.Random;

public abstract class EnergySource {
    private String energyName;
    private int energyPrice;
    private int energyEmission;
    private int energyOutput;

    public EnergySource(String energyName, int energyPrice, int energyEmission, int energyOutput){
        this.energyName = energyName;
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

    public int getEnergyPrice() {
        double newEnergyPrice = energyPrice;

        for (int i = 0; i < Game.instance.getGameYear(); i++) {
            if(energyEmission <= 0){
                int decreasePercent = randomPercent(5, 2);
                newEnergyPrice -= decreasePercent;
            } else {
                int increasePercent = randomPercent(6, 1);
                newEnergyPrice += increasePercent;
            }
        }
        return (int)Math.round(newEnergyPrice);
    }

    public void setEnergyPrice(int energyPrice) {
        this.energyPrice = energyPrice;
    }

    public int getEnergyEmission() {
        return energyEmission;
    }

    public void setEnergyEmission(int energyEmission) {
        this.energyEmission = energyEmission;
    }

    public int getEnergyOutput() {
        return energyOutput;
    }

    public void setEnergyOutput(int energyOutput) {
        this.energyOutput = energyOutput;
    }

    public int randomPercent(int max, int min){
        Random rand = new Random();
        double random_int = rand.nextInt(max - min + 1) + 2/100;
        double procent = (random_int/100);
        int fivePercent = (int)Math.round(energyPrice * procent);
        return fivePercent;
    }
}
