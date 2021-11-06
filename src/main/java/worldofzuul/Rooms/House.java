package worldofzuul.Rooms;

import worldofzuul.*;
import worldofzuul.EnergyConsumer.*;

public class House extends Room {
    private double energyRequirement;
    private Car car;
    private Heating heater;

    public House(double energyRequirement) {
        super("in your house", "house");
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
        System.out.println("TODO: Create better description of house(mention car and house heating)");

        // TODO: Should print something about the energyconsumers and if the energyrequirement is fulfilled

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
                            "and costs you annualy $" + heater.getYearlyCost() +
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
