package worldofzuul.Rooms;

import worldofzuul.EnergySources.*;
import worldofzuul.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildArea extends Room {
    private ArrayList<EnergySource> energySources = new ArrayList<>();

    public BuildArea() {
        super("build area", "at the build area, here you find the energy sources you have built");
    }

    public void addEnergySource(EnergySource e) {
        energySources.add(e);
    }

    public double getYearlyEnergyProduction() {
        return energySources.stream().mapToDouble(EnergySource::getOutput).sum();
    }

    public double getYearlyEmissions() {
        return energySources.stream().mapToDouble(EnergySource::getEmission).sum();
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
                .mapToDouble(EnergySource::getCapacity)
                .sum() + "kWh of battery capacity");

        // Print renewable energy sources
        if (energySources.stream().anyMatch(EnergySource::isRenewable)) {
            System.out.println("Installed renewable capacity: ");
            for (EnergySource e : energySources) {
                if (e.isBattery() || !e.isRenewable())
                    continue;
                System.out.println("\t - " + e.getSize().upperCaseName() + " " + e.getName() + " producing " + e.getOutput() + "kWh pr. year");
            }
        }

        // Print fossil energy status
        if (energySources.stream().anyMatch(EnergySource::isFossil)) {
            System.out.println("Bought fossil based energy: ");
            energySources.stream()
                    .filter(EnergySource::isFossil)
                    .collect(Collectors.groupingBy(EnergySource::getName))
                    .forEach((k, v) -> System.out.printf("\t - %.1fkWh of %s energy\n",
                            v.stream().mapToDouble(EnergySource::getOutput).sum(),
                            k.toLowerCase(Locale.ROOT)));
        }

        System.out.println(getExitString());
    }

    @Override
    public void getInfoAbout(String secondWord) {
        EnergySource foundEnergySource = null;
        for (var e : energySources) {
            var energyName = e.getName().toLowerCase(Locale.ROOT);
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
                .mapToDouble(EnergySource::getOutput)
                .sum();
    }

    public double getEnergySalesPrice() {
        // TODO: Make this respond to the installed battery capacity
        // Im thinking 0.25 * (1 + clamp(battery_capacity/renewable_energy_capacity, 0, 2))
        return 0.25;
    }
}
