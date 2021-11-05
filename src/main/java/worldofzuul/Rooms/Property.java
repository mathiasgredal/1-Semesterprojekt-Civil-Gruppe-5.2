package worldofzuul.Rooms;

import worldofzuul.EnergySources.EnergySource;
import worldofzuul.Game;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class Property extends Room {
    private ArrayList<EnergySource> energySources = new ArrayList<>();

    public Property(String description) {
       super(description);
   }

   public void addEnergySources(EnergySource e){
       if (e.getEnergyEmission()<=0){
           this.energySources.add(e);
       }
   }

   public void viewEnergySources(){
       for (int i = 0; i < energySources.size() ; i++) {
           System.out.println(energySources.get(i).getEnergyName() + ", " + energySources.get(i).getEnergyPrice() + ", " + energySources.get(i).getEnergyEmission() + ", " + energySources.get(i).getEnergyOutput());
       }
   }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());

        viewEnergySources();

        //Print exits, the rooms you can go to.
        System.out.println(getExitString());
    }
}

