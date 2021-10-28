package worldofzuul;

import java.util.ArrayList;

public class Player {
    private int playerEconomy;
    private ArrayList<EnergySource> energySources;

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

    //Runs through the length of the instantiated arraylist, adds all the emission ints from the arraylists objects into the totalEmission.
    public int calculateEmission(){
        int totalEmission = 0;
        for(int i = 0; i < energySources.size(); i++){
            totalEmission += energySources.get(i).getEnergyEmission();
        }
        return totalEmission;
    }

    public void printEnergySources(){
        for(int i = 0; i < energySources.size(); i++){
            System.out.println(energySources.get(i).getEnergyName() + ", " + energySources.get(i).getEnergyPrice() + ", " + energySources.get(i).getEnergyEmission() + ", " + energySources.get(i).getEnergyOutput());
        }
    }
}
