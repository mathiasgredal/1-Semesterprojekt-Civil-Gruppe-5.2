package worldofzuul.Items;

import worldofzuul.Game;

import java.util.Random;

/**
 *
 */
public class EnergySource implements Buyable {
    private String name = "";
    private String description = "";
    private EnergySourceSize size = EnergySourceSize.SMALL;
    private double price = 0;
    private double emission = 0;
    private double output = 0;
    private double capacity = 0;

    /**
     * This is the physical size and position of the object in the build area
     */
    private int posX = 0;
    private int posY = 0;

    /**
     * These values are logged each year for an eventual calculation of ROI
     */
    private double totalGeneratedEnergy = 0;
    private double totalGeneratedMoney = 0;

    /**
     * A no-arg constructor needed by the SnakeYAML library.
     */
    public EnergySource() {
    }

    /**
     * Constructor for initializing values in energysource
     */
    public EnergySource(String energyName, String energyDescription, EnergySourceSize size, double energyPrice, double energyEmission, double energyOutput, double energyCapacity) {
        this.name = energyName;
        this.description = energyDescription;
        this.size = size;
        this.price = energyPrice;
        this.emission = energyEmission;
        this.output = energyOutput;
        this.capacity = energyCapacity;
    }

    /**
     * Getter for energysource name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for energysource name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for energysource price, with built-in price developments for energysources
     * Fossil energysource increase in price over the years, and renewables fall in price
     *
     * @return Price in dkk.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        return getName() +
                ", Costs: " + "$" + getPrice() +
                ", Emits: " + getEmission() + "g/year CO\u2082" +
                ", Outputs: " + getOutput() + " kWh.";
    }

    /**
     * Sets the energy price to a specified value
     *
     * @param price The price in dkk
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for energysource emission
     *
     * @return Emission in kg CO2
     */
    public double getEmission() {
        return emission;
    }

    /**
     * Setter for energysource emission
     *
     * @param emission Emission in kg CO2
     */
    public void setEmission(double emission) {
        this.emission = emission;
    }

    /**
     * Getter for energy output
     *
     * @return Produced kWh of energy over a year
     */
    public double getOutput() {
        return output;
    }

    /**
     * Setter for energy output
     *
     * @param output kWh pr. year
     */
    public void setOutput(double output) {
        this.output = output;
    }

    /**
     * Logs the produced energy and associated sales price for this year
     *
     * @param electricityPrice electricity price in dkk pr. kWh
     */
    public void addYearlyEnergyProduction(double electricityPrice) {
        totalGeneratedEnergy += output;
        totalGeneratedMoney += output * electricityPrice;
    }

    /**
     * Getter for total generated energy over the lifetime of the energysource in kWh
     */
    public double getTotalGeneratedEnergy() {
        return totalGeneratedEnergy;
    }

    /**
     * Getter for total generated money in dkk over the lifetime of the energysource
     */
    public double getTotalGeneratedMoney() {
        return totalGeneratedMoney;
    }

    /**
     * Getter for the size of the energysource
     */
    public EnergySourceSize getSize() {
        return size;
    }

    /**
     * @return Returns true if the energysource is renewable and otherwise false
     */
    public boolean isRenewable() {
        return !(emission > 0);
    }

    /**
     * @return Returns true if the energysource is fossil and otherwise false
     */
    public boolean isFossil() {
        return emission > 0;
    }

    /**
     * @return Returns true if the energysource is a battery and otherwise false
     */
    public boolean isBattery() {
        return capacity > 0;
    }

    /**
     * Prints the description of the energysource
     */
    public void printDescription() {
        System.out.println(description);
    }

    /**
     * Getter for energy source description
     */
    public String getDescription() {
        return description;
    }

    /**
     * A utility function for generating a random percentage
     *
     * @param max The maximum percentage to increase price by
     * @param min The minimum percentage to increase price by
     * @return The new price
     */
    public int randomPercent(int max, int min) {
        Random rand = new Random();
        double random_int = rand.nextInt(max - min + 1) + 2.0 / 100.0;
        double procent = (random_int / 100);
        return (int) Math.round(price * procent);
    }

    /**
     * Setter for energysource size
     */
    public void setSize(EnergySourceSize size) {
        this.size = size;
    }

    /**
     * Setter for energysource description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the energysource capacity, mainly used for batteries
     *
     * @return The capacity in kWh
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Setter for energysource capacity
     */
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
