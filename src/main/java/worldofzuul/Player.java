package worldofzuul;

/**
 * The player class, used to manage player specific resources mainly ecomony and recap data
 */
public class Player {
    private double playerEconomy = 300000;
    private final double yearlyIncome = 10000;

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
