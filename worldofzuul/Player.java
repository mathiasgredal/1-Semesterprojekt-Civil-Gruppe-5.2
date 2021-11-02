package worldofzuul;

import worldofzuul.EnergySources.EnergySource;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int playerEconomy;
    private ArrayList<EnergySource> energySources;
    private HashMap<Integer, ArrayList> recapEnergySources = new HashMap<Integer, ArrayList>();

    public Player(int playerEconomy, ArrayList energySources){
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
        for(int i = 0; i < energySources.size(); i++){
            totalOutput = energySources.get(i).getEnergyOutput();
        }
        return totalOutput;
    }

    public void printEnergySources(){
        for(int i = 0; i < energySources.size(); i++){
            System.out.println(energySources.get(i).getEnergyName() + ", " + energySources.get(i).getEnergyPrice() + ", " + energySources.get(i).getEnergyEmission() + ", " + energySources.get(i).getEnergyOutput());
        }
    }
}
