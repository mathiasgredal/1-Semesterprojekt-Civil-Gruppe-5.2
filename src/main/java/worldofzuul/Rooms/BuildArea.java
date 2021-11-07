package worldofzuul.Rooms;

import worldofzuul.Game;
import worldofzuul.Items.EnergySource;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BuildArea extends Room {
    private ArrayList<EnergySource> energySources = new ArrayList<>();

    public BuildArea() {
        super("build area", "at the build area, here you find the energy sources you have built");
    }

    public void addEnergySource(EnergySource e) {
        energySources.add(e);
    }

    public ArrayList<EnergySource> getEnergySources() {
        return energySources;
    }
    
    public double getYearlyEnergyProduction() {
        double totalEnergyProduction = 0;

        for (var e : energySources) {
            totalEnergyProduction += e.getOutput();
        }
        return totalEnergyProduction;
    }

    public double getYearlyEnergyProductionRenewable() {
        double totalEnergyProduction = 0;

        for (var e : energySources) {
            if (e.isRenewable())
                totalEnergyProduction += e.getOutput();
        }
        return totalEnergyProduction;
    }

    public double getYearlyEnergyProductionFossil() {
        double totalEnergyProduction = 0;

        for (var e : energySources) {
            if (e.isFossil())
                totalEnergyProduction += e.getOutput();
        }
        return totalEnergyProduction;
    }

    public double getYearlyEmissions() {
        double totalEmission = 0;

        for (var e : energySources) {
            totalEmission += e.getEmission();
        }

        return totalEmission;
    }

    public void removeFossilEnergySources() {
        ArrayList<EnergySource> renewableEnergySources = new ArrayList<>();
        for (var e : energySources) {
            if (e.isRenewable())
                renewableEnergySources.add(e);
        }

        this.energySources = renewableEnergySources;
    }

    public void addYearlyEnergyProductionToEnergySources() {
        for (var e : energySources) {
            e.addYearlyEnergyProduction(getEnergySalesPricePrkWh());
        }
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.print(getLongDescription());
        System.out.println("The build area currently has " + getYearlyEnergyProduction() + "kWh pr. year of renewable energy production");
        System.out.println("There is " + getTotalBatteryCapacity() + "kWh of battery capacity");

        // Print renewable energy sources
        boolean hasRenewable = false;
        for (var e : energySources) {
            if (e.isRenewable()) {
                hasRenewable = true;
                break;
            }
        }

        if (hasRenewable) {
            System.out.println("Installed renewable capacity: ");
            for (EnergySource e : energySources) {
                if (e.isBattery() || !e.isRenewable())
                    continue;
                System.out.println("\t - " + e.getSize().upperCaseName() + " " + e.getName() + " producing " + e.getOutput() + "kWh pr. year");
            }
        }

        // Print renewable energy sources
        boolean hasFossil = false;
        for (var e : energySources) {
            if (e.isFossil()) {
                hasFossil = true;
                break;
            }
        }

        if (hasFossil) {
            System.out.println("Bought fossil based energy: ");

            // This statement does 3 things:
            //  - Filters the stream, if they are not fossil
            //  - Collects the filtered stream into a map,
            //      which has a key based on the energysource name and a value which is the energysource
            //  - Iterates through the map, and prints how much energy each energysource has.
            //     the iterator uses a lambda method, where k is the map key and v is the map value
            // This code is an example of the functional programming paradigm,
            // and makes a task like grouping an array into a map, a lot simpler.
            energySources.stream()
                    .filter(EnergySource::isFossil)
                    .collect(Collectors.groupingBy(EnergySource::getName))
                    .forEach((k, v) -> {
                        double totalEnergyOutput = 0;
                        for (var e : v) {
                            totalEnergyOutput += e.getOutput();
                        }
                        System.out.printf("\t - %.1fkWh of %s energy\n", totalEnergyOutput, k.toLowerCase());
                    });
        }

        System.out.println(getExitString());
    }

    public double getTotalBatteryCapacity() {
        double sum = 0;
        for (var energySource : energySources)
            sum += energySource.getCapacity();
        return sum;
    }

    @Override
    public void getInfoAbout(String secondWord) {
        EnergySource foundEnergySource = null;
        for (var e : energySources) {
            var energyName = e.getName().toLowerCase();
            var firstWord = energyName.contains(" ") ? energyName.split(" ")[0] : energyName;
            if (firstWord.equals(secondWord.toLowerCase())) {
                foundEnergySource = e;
            }
        }

        if (foundEnergySource != null) {
            foundEnergySource.printDescription();
        } else {
            super.getInfoAbout(secondWord);
        }
    }

    public double getEnergySalesPricePrkWh() {
        // Due to load shifting we store electricity and sell it when the price is higher
        // the increase sales price is calculated by: 0.25 * (1 + clamp(capacity/renewable_production, 0, 1)
        double salesPriceFactor = Math.max(1, Math.min(2, 1 + getTotalBatteryCapacity() / getYearlyEnergyProductionRenewable()));
        return 0.25 * salesPriceFactor;
    }
}
