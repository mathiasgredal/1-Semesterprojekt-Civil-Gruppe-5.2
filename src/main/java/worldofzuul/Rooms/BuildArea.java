package worldofzuul.Rooms;

import worldofzuul.EnergySources.*;
import worldofzuul.Game;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class BuildArea extends Room {
    private ArrayList<EnergySource> energySources = new ArrayList<>();

    public BuildArea() {
        super("at the build area, here you find the energy sources you have built", "build area");
    }

    public void addEnergySource(EnergySource e) {
        energySources.add(e);
    }

    public double getYearlyEnergyProduction() {
        return energySources.stream().mapToDouble(EnergySource::getEnergyOutput).sum();
    }

    public double getYearlyEmissions() {
        return energySources.stream().mapToDouble(EnergySource::getEnergyEmission).sum();
    }

    public void removeFossilEnergySources() {
        energySources = (ArrayList<EnergySource>) energySources.stream()
                .filter(EnergySource::isRenewable)
                .collect(Collectors.toList());
    }

    public void addYearlyEnergyProductionToEnergySources() {
        energySources.forEach(e -> e.addYearlyEnergyProduction(getEnergySalesPrice()));
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.print(getLongDescription());
        System.out.println("The build area currently has " + getYearlyEnergyProduction() + "kWh pr. year of energy production");
        System.out.println("There is " + energySources.stream()
                .filter(e -> e instanceof BatteryEnergy)
                .mapToDouble(e -> ((BatteryEnergy) e).getEnergyStorage())
                .sum() + "kWh of battery capacity");

        if (energySources.stream().anyMatch(EnergySource::isRenewable)) {
            System.out.println("Installed renewable capacity: ");
            for (EnergySource e : energySources) {
                if (e instanceof BatteryEnergy || !e.isRenewable())
                    continue;
                System.out.println("\t - " + e.getSize().upperCaseName() + " " + e.getEnergyName() + " producing " + e.getEnergyOutput() + "kWh pr. year");
            }
        }

        if (energySources.stream().anyMatch(EnergySource::isFossil)) {
            System.out.println("Bought fossil based energy: ");
            // TODO: Better abstractions would eliminate this mess, sigh...
            double coal = getEnergyOutputFrom(CoalEnergy.class);
            double oil = getEnergyOutputFrom(OilEnergy.class);
            double gas = getEnergyOutputFrom(GasEnergy.class);

            if (coal != 0) System.out.println("\t - " + coal + "kWh of coal energy");
            if (oil != 0) System.out.println("\t - " + oil + "kWh of oil energy");
            if (gas != 0) System.out.println("\t - " + gas + "kWh of gas energy");
        }

        // Print fossil energy sources
        System.out.println(getExitString());
    }

    @Override
    public void getInfoAbout(String secondWord) {
        boolean foundInfo = false;
        EnergySource foundEnergySource = null;
        for (var e : energySources) {
            var energyName = e.getEnergyName().toLowerCase(Locale.ROOT);
            var firstWord = energyName.contains(" ") ? energyName.split(" ")[0] : energyName;
            if (firstWord.equals(secondWord.toLowerCase(Locale.ROOT))) {
                foundEnergySource = e;
            }
        }

        if (foundEnergySource != null) {
            foundEnergySource.printDescription();
        } else {
            super.getInfoAbout(secondWord);
        }
    }

    public double getEnergyOutputFrom(Class<?> type) {
        return energySources.stream()
                .filter(type::isInstance)
                .mapToDouble(EnergySource::getEnergyOutput)
                .sum();
    }

    public double getEnergySalesPrice() {
        // TODO: Make this respond to the installed battery capacity
        // Im thinking 0.25 * (1 + clamp(battery_capacity/renewable_energy_capacity, 0, 2))
        return 0.25;
    }
}
