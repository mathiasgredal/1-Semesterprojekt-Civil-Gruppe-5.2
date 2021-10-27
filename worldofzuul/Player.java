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

    public void addEnergySource(GasEnergy e){
        energySources.add(e);
    }

    //TODO: Udfyld metoden med beregning for spillerens emission.
    public int calculateEmission(ArrayList arrayList){
        return 0;
    }

    public void printEnergySources(){
        for(int i = 0; i < energySources.size(); i++){
            System.out.println(energySources.get(i).getEnergyPrice() + ", " + energySources.get(i).getEnergyEmission() + ", " + energySources.get(i).getEnergyOutput());
        }
    }
}
