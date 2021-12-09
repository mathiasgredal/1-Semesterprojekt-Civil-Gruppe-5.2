package worldofzuul.Rooms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import worldofzuul.Game;
import worldofzuul.Items.EnergySource;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BuildArea extends Room {

    private ObservableList<EnergySource> energySources = FXCollections.observableArrayList();

    // The minimal energy sales price: https://www.vivaenergi.dk/elsalg
    // This fluctuates between 0.3 DKK pr. kWh and 0.8 DKK pr. kWh during the day
    private double baseEnergyPriceDKK = 0.30;

    public BuildArea() {
        super("build area", "at the build area, here you find the energy sources you have built");
    }

    /**
     * Adds an energysource to the list of energysources
     */
    public void addEnergySource(EnergySource e) {
        // Clone the energysource, to prevent mutation when setting a new position
        var energySourceClone = new EnergySource(e.getName(), e.getDescription(), e.getSize(), e.getPrice(), e.getEmission(), e.getOutput(), e.getCapacity(), e.getTextureURL(), e.getWidth(), e.getHeight());
        energySources.add(energySourceClone);
    }

    /**
     * Gets a copy of the list of energysources
     */
    public ObservableList<EnergySource> getEnergySources() {
        return energySources;
    }

    /**
     * Gets the total energyproduction this year for all the energysources
     *
     * @return kWh
     */
    public double getYearlyEnergyProduction() {
        double totalEnergyProduction = 0;

        for (var e : energySources) {
            totalEnergyProduction += e.getOutput();
        }
        return totalEnergyProduction;
    }

    /**
     * Gets the total energyproduction this year for the renewable energysources
     *
     * @return kWh
     */
    public double getYearlyEnergyProductionRenewable() {
        double totalEnergyProduction = 0;

        for (var e : energySources) {
            if (e.isRenewable())
                totalEnergyProduction += e.getOutput();
        }
        return totalEnergyProduction;
    }

    /**
     * Gets the total energyproduction this year for the fossil energysources
     *
     * @return kWh
     */
    public double getYearlyEnergyProductionFossil() {
        double totalEnergyProduction = 0;

        for (var e : energySources) {
            if (e.isFossil())
                totalEnergyProduction += e.getOutput();
        }
        return totalEnergyProduction;
    }

    /**
     * Gets the total emissions for this year
     *
     * @return emission in kg CO2
     */
    public double getYearlyEmissions() {
        double totalEmission = 0;

        for (var e : energySources) {
            totalEmission += e.getEmission();
        }

        return totalEmission;
    }

    /**
     * Removes fossil energysources from the energysource list
     */
    public void removeFossilEnergySources() {
        ArrayList<EnergySource> renewableEnergySources = new ArrayList<>();
        for (var e : energySources) {
            if (e.isRenewable())
                renewableEnergySources.add(e);
        }

        this.energySources.clear();
        this.energySources.addAll(renewableEnergySources);
    }

    /**
     * Logs the produced energy and sales from each energysource to them self
     */
    public void addYearlyEnergyProductionToEnergySources() {
        for (var e : energySources) {
            if (e.isBattery()) {
                e.addTotalGeneratedMoney(getEnergySalesPricePrkWh(e.getCapacity()) - getBaseEnergyPriceDKK());
            } else {
                e.addYearlyEnergyProduction(getEnergySalesPricePrkWh());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * Gets the battery capacity
     *
     * @return Total stored energy in kWh
     */
    public double getTotalBatteryCapacity() {
        double sum = 0;
        for (var energySource : energySources)
            sum += energySource.getCapacity();
        return sum;
    }

    /**
     * {@inheritDoc}
     */
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

    public double getBaseEnergyPriceDKK() {
        return baseEnergyPriceDKK;
    }

    /**
     * Energy sales price varies with the players installed battery capacity
     * Due to load shifting we store electricity when price is low and sell it when the price is higher
     * This method uses a formula to estimate this price
     * 0.25 * (1 + clamp(capacity/renewable_production, 0, 1)
     *
     * @return The sales price in DKK pr. kWh
     */
    public double getEnergySalesPricePrkWh(double batteryCapacityPrKWh) {
        // Prevent a divide by zero
        if (getYearlyEnergyProductionRenewable() == 0) {
            return baseEnergyPriceDKK;
        } else {
            // Use the previously defined formula, for sales price estimation
            double salesPriceFactor = Math.max(1, Math.min(2, 1 + batteryCapacityPrKWh / getYearlyEnergyProductionRenewable()));
            return baseEnergyPriceDKK * salesPriceFactor;
        }
    }

    public double getEnergySalesPricePrkWh() {
        return getEnergySalesPricePrkWh(getTotalBatteryCapacity());
    }
}
