package worldofzuul;

import worldofzuul.EnergySources.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int playerEconomy;
    private ArrayList<EnergySource> energySources;
    private HashMap<Integer, ArrayList> recapEnergySources = new HashMap<Integer, ArrayList>();
    private HashMap<Integer, Integer> recapEnergyEmission = new HashMap<Integer, Integer>();

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

    public void addEnergySource(int year, EnergySource e){
        energySources.add(e);
        recapEnergySources.put(year, energySources);
    }

    /**
     * Runs through the length of the instantiated arraylist, adds all the emission ints from the arraylists objects into the totalEmission.
     * @return The total emission
     */
    public int calculateEmission(int year){
        int totalEmission = 0;
        for (EnergySource energySource : energySources) {
            totalEmission += energySource.getEnergyEmission();
        }
        recapEnergyEmission.put(year, totalEmission);
        for (int i = 0; i < recapEnergyEmission.size(); i++) {
            System.out.println(recapEnergyEmission.get(i));
        }
        return totalEmission;
    }


    public void clearEnergySources(int year){
        recapEnergySources.put(year, energySources);
        energySources.removeIf(s -> s.getEnergyEmission() > 0);
    }

    /**
     * Runs through the length of the instantiated arraylist, adds all the energy output ints from the arraylists objects into the totalOutput variable.
     * @return The total energy output
     */
    public int getTotalEnergyOutput(){
        int totalOutput = 0;
        for (EnergySource energySource : energySources) {
            totalOutput += energySource.getEnergyOutput();
        }
        return totalOutput;
    }

    public void printEnergySources(){
        for (EnergySource energySource : energySources) {
            System.out.println(energySource.getEnergyName() + ", " + energySource.getEnergyPrice() + ", " + energySource.getEnergyEmission() + ", " + energySource.getEnergyOutput());
        }
    }
}
