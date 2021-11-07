package worldofzuul.Rooms;

import worldofzuul.Items.EnergyConsumer.*;
import worldofzuul.Game;

import java.util.Locale;

public class House extends Room {
    private final double energyRequirement;
    private final Car car;
    private final Heating heater;

    public House(double energyRequirement) {
        super("house", "in your house");
        this.energyRequirement = energyRequirement;
        this.car = new InternalCombustionCar();
        this.heater = new GasHeating();
    }

    public double getEnergyRequirement() {
        return energyRequirement + car.getYearlyEnergyConsumption() + heater.getYearlyEnergyConsumption();
    }

    public double getYearlyEmissions() {
        return car.getYearlyEmission() + heater.getYearlyEmission();
    }

    public double getYearlyCost() {
        return car.getYearlyCost() + heater.getYearlyCost();
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());

        System.out.println("Your house has an annual energy requirement of " + getEnergyRequirement() + "kWh, \n"
                + "emission of " + getYearlyEmissions() + "g/CO2 and cost of $" + getYearlyCost() + "\n");

        System.out.println("The primary means of transport for your house is an " + car.getName().toLowerCase(Locale.ROOT)
                + ",\nand heating is provided by a " + heater.getName().toLowerCase(Locale.ROOT) + ".\n");

        //Print exits, the rooms you can go to.
        System.out.println(getExitString());
    }

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
                    System.out.println("This is your electric car, which runs purely on electricity\n" +
                            " and consumes " + car.getYearlyEnergyConsumption() +
                            "kWh annualy. \n By having this you are both saving money and emitting less CO2");
                }
            }
            default -> super.getInfoAbout(secondWord);
        }

    }
}
