package worldofzuul.Rooms;

import worldofzuul.*;

public class House extends Room{
    private int energyNeed;

    public House(String description, int energyNeed){
        super(description);
        this.energyNeed = energyNeed;
    }

    public int getEnergyNeed() {
        return energyNeed;
    }

    public void setEnergyNeed(int energyNeed) {
        this.energyNeed = energyNeed;
    }

    @Override
    public void printEnterRoomString(Game game) {
        System.out.println(getLongDescription());
        System.out.println("Current energy supplier(s)/source(s): ");
        game.getPlayer().printEnergySources();

        //Checks if the players total energy output from the bought energy sources is lower than the needed energy from the house.
        if(game.getPlayer().getTotalEnergyOutput() < getEnergyNeed()){
            System.out.println("\nYou have not fulfilled the energy requirement, you need: " + (getEnergyNeed()-game.getPlayer().getTotalEnergyOutput()) + " kWh");
        }
        else{
            System.out.println("\nYou have fulfilled the requirement");
        }

        //Print exits, the rooms you can go to.
        System.out.println(getExitString());
    }
}
