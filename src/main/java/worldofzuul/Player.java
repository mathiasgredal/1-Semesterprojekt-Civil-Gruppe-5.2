package worldofzuul;

import worldofzuul.EnergySources.*;

import java.util.ArrayList;

public class Player {
    private int playerEconomy;
    private ArrayList<EnergySource> energySources;

    public Player(int playerEconomy, ArrayList<EnergySource> energySources){
        this.playerEconomy = playerEconomy;
        this.energySources = energySources;
    }

    public int getPlayerEconomy() {
        return playerEconomy;
    }

    public void setPlayerEconomy(int playerEconomy) {
        this.playerEconomy = playerEconomy;
    }

    public void addEnergySource(EnergySource e){
        energySources.add(e);
    }

    /**
     * Runs through the length of the instantiated arraylist, adds all the emission ints from the arraylists objects into the totalEmission.
     * @return The total emission
     */
    public int calculateEmission(){
        int totalEmission = 0;
        for (EnergySource energySource : energySources) {
            totalEmission += energySource.getEnergyEmission();
        }
        return totalEmission;
    }

    /**
     * Runs through the length of the instantiated arraylist, adds all the energy output ints from the arraylists objects into the totalOutput variable.
     * @return The total energy output
     */
    public int getTotalEnergyOutput(){
        int totalOutput = 0;
        for (EnergySource energySource : energySources) {
            totalOutput = energySource.getEnergyOutput();
        }
        return totalOutput;
    }

    public void printEnergySources(){
        for (EnergySource energySource : energySources) {
            System.out.println(energySource.getEnergyName() + ", " + energySource.getEnergyPrice() + ", " + energySource.getEnergyEmission() + ", " + energySource.getEnergyOutput());
        }
    }
}
