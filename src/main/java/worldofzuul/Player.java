package worldofzuul;

import worldofzuul.EnergySources.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private double playerEconomy = 300000;
    private double yearlyIncome = 10000;

    public Player() {
    }

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

    /**
     * Inserts money into account
     */
    public void insertMoney(double amount) {
        playerEconomy += amount;
    }

    public double getYearlyIncome() {
        return yearlyIncome;
    }
}
