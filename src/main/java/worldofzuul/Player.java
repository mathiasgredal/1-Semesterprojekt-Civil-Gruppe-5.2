package worldofzuul;

import worldofzuul.EnergySources.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int playerEconomy;
    private ArrayList<EnergySource> energySources;
    private HashMap<Integer, ArrayList<EnergySource>> recapEnergySources = new HashMap<Integer, ArrayList<EnergySource>>();
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

    public void addEnergySource(EnergySource e){
        energySources.add(e);
    }

    public void transferEnergySources(int year){
        int totalEmissionPerYear = 0;

        recapEnergySources.put(year, energySources);

        //Iterates through the hashmaps keys.
        for(int i = 0; i < recapEnergySources.size(); i++){
            //The nested loop sums up the arraylist' energy emission of all energy sources
            for(int j = 0; j < recapEnergySources.get(i).size(); j++){
                totalEmissionPerYear += recapEnergySources.get(i).get(j).getEnergyEmission();
            }
        }

        //Collects and puts all calculated emission for every year into the hashmap , Used in the recap window.
        recapEnergyEmission.put(year, totalEmissionPerYear);
        for(int re : recapEnergyEmission.keySet()){
            System.out.println(re+ ": " + recapEnergyEmission.get(re));
        }
    }

    /**
     * Runs through the length of the instantiated arraylist, adds all the emission ints from the arraylists objects into the totalEmission.
     * @return The total emission
     */
    public int calculateEmission(){
        int totalEmission = 0;

        for(int re : recapEnergyEmission.keySet()){
            totalEmission += recapEnergyEmission.get(re);
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
