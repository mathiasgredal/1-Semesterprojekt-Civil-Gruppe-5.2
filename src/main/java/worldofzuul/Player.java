package worldofzuul;

import worldofzuul.Items.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The player class, used to manage player specific resources mainly ecomony and recap data
 */
public class Player {
    private double playerEconomy = 300000;
    private final double yearlyIncome = 10000;
    private HashMap<Integer, ArrayList<EnergySource>> recapEnergySources = new HashMap<Integer, ArrayList<EnergySource>>();
    private HashMap<Integer, Integer> recapEnergyEmission = new HashMap<Integer, Integer>();

    /**
     * No-arg constructor for player, using the defined value
     */
    public Player() {
    }

    /**
     * Constructs the player with the set economy
     *
     * @param playerEconomy The dollar amount the player should start with
     */
    public Player(int playerEconomy) {
        this.playerEconomy = playerEconomy;
    }

    /**
     * Gets the amount of money the player has
     *
     * @return The amount of money
     */
    public double getPlayerEconomy() {
        return playerEconomy;
    }

    /**
     * Withdraws money from account
     *
     * @return Returns true if withdrawal was successful
     */
    public boolean withdrawMoney(double amount) {
        if (playerEconomy > amount) {
            playerEconomy -= amount;
            return true;
        } else {
            return false;
        }

    }
    public int calculateEmission(){
        int totalEmission = 0;

        for(int re : recapEnergyEmission.keySet()){
            totalEmission += recapEnergyEmission.get(re);
        }

        return totalEmission;
    }


    public void transferEnergySources(int year, ArrayList<EnergySource> energySources){
        int totalEmissionPerYear = 0;

        recapEnergySources.put(year, energySources);

        //Iterates through the hashmaps keys.
        for(int i = 0; i < recapEnergySources.size(); i++){
            //The nested loop sums up the arraylist' energy emission of all energy sources
            for(int j = 0; j < recapEnergySources.get(i).size(); j++){
                totalEmissionPerYear += recapEnergySources.get(i).get(j).getEmission();
            }
        }

        //Collects and puts all calculated emission for every year into the hashmap , Used in the recap window.
        recapEnergyEmission.put(year, totalEmissionPerYear);
    }

    public void saveRecapData(int year, ArrayList<EnergySource> energySources){
        recapEnergySources.put(year, energySources);
    }

    /**
     * Inserts money into account
     */
    public void insertMoney(double amount) {
        playerEconomy += amount;
    }

    /**
     * Getter for the players yearly income
     *
     * @return The dollar amount for the yearly income
     */
    public double getYearlyIncome() {
        return yearlyIncome;
    }
}
