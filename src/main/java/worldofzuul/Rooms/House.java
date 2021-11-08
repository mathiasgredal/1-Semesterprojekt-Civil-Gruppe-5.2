package worldofzuul.Rooms;

import worldofzuul.Exceptions.CannotBuyItemMoreThanOnceException;
import worldofzuul.Items.EnergyConsumer.*;
import worldofzuul.Game;

/**
 * The player house
 */
public class House extends Room {
    private Car car;
    private Heating heater;
    private final double energyRequirement;

    /**
     * Constructor for the house
     *
     * @param energyRequirement The base energy requirement in kWh pr. year
     */
    public House(double energyRequirement) {
        super("house", "in your house");
        this.energyRequirement = energyRequirement;
        this.car = new InternalCombustionCar();
        this.heater = new GasHeating();
    }

    /**
     * Gets the total energy requirement of house + consumers
     *
     * @return Energy requirement in kWh pr. year
     */
    public double getEnergyRequirement() {
        return energyRequirement + car.getYearlyEnergyConsumption() + heater.getYearlyEnergyConsumption();
    }

    /**
     * Gets the total emissions for all consumers
     *
     * @return Emission in grams of CO2 pr. year
     */
    public double getYearlyEmissions() {
        return car.getYearlyEmission() + heater.getYearlyEmission();
    }

    /**
     * Getter for the yearly cost of all consumers
     *
     * @return The dollar amount
     */
    public double getYearlyCost() {
        return car.getYearlyCost() + heater.getYearlyCost();
    }

    /**
     * Upgrades an energy consumer on the house to the provided one
     *
     * @param consumer The new consumer to add to the house
     * @throws CannotBuyItemMoreThanOnceException if the house already has the consumer
     */
    public void addEnergyConsumer(EnergyConsumer consumer) throws CannotBuyItemMoreThanOnceException {
        // There are only specific upgrade paths allowed for house,
        // and you should not be able to buy something you already own.
        if (car instanceof InternalCombustionCar && consumer instanceof ElectricCar) {
            car = (Car) consumer;
        } else if (heater instanceof GasHeating && consumer instanceof HeatPump) {
            heater = (Heating) consumer;
        } else {
            throw new CannotBuyItemMoreThanOnceException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());

        System.out.println("Your house has an annual energy requirement of " + getEnergyRequirement() + "kWh, \n"
                + "emission of " + getYearlyEmissions() + "g/CO2 and cost of $" + getYearlyCost() + "\n");

        System.out.println("The primary means of transport for your house is an " + car.getName().toLowerCase()
                + ",\nand heating is provided by a " + heater.getName().toLowerCase() + ".\n");

        //Print exits, the rooms you can go to.
        System.out.println(getExitString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getInfoAbout(String secondWord) {
        switch (secondWord) {
            case "car", "automobile" -> {
                // TODO: Perhaps move this to the car class
                if (car instanceof InternalCombustionCar) {
                    System.out.println("This is your internal combustion car, which runs on fossil fuels\n" +
                            "and has an annual cost of $" + car.getYearlyCost() +
                            " in gasoline. \nIf you generate en excess amount of renewable energy,\n" +
                            "you could save money by buying an electric car.");
                } else if (car instanceof ElectricCar) {
                    System.out.println("This is your electric car, which runs purely on electricity\n" +
                            " and consumes " + car.getYearlyEnergyConsumption() +
                            "kWh annualy. \n By having this you are both saving money and emitting less CO2");
                }
            }
            case "heater", "heating" -> {
                // TODO: Perhaps move this to the heating class
                if (heater instanceof GasHeating) {
                    System.out.println("Your house is heated by natural gas" +
                            " and costs you annualy $" + heater.getYearlyCost() +
                            "\nBy buying a heatpump, whose price is partially subsidized by the government,\n" +
                            "you can use your excess renewable energy to heat your house\n" +
                            "and save money on natural gas, while emitting less CO2");
                } else if (heater instanceof HeatPump) {
                    System.out.println("Your house is heated by a heat pump\n" +
                            " and consumes " + heater.getYearlyEnergyConsumption() +
                            "kWh annualy. \n By having this you are both saving money and emitting less CO2");
                }
            }
            default -> super.getInfoAbout(secondWord);
        }

    }
}
