package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import worldofzuul.Game;

import java.text.DecimalFormat;

public class RecapController {
    /**
     * Makes an object of the DecimalFormat class and uses it to make double variables decrease the decimalnummer down to two
     **/
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Objects instantiated from the fxml document
     **/
    @FXML
    private Label emissionScore, moneyEarnedScore, money, highScore;

    /**
     * This is the first method that is run
     */
    @FXML
    public void initialize(){
        // make and assign values to the variables
        double emission = Game.instance.getPlayer().calculateEmission();
        double moneyEarned = Game.instance.getSoldEnergyPrice();
        double excessMoney = Game.instance.getPlayer().getPlayerEconomy();
        int highscore = (int)((50000/(emission/1000))+moneyEarned+excessMoney);

        //uses decimalFormat.format
        //Sets the lable text to the assigned valuse of the variables and some text
        emissionScore.setText(decimalFormat.format(emission) + " kg CO\u2082");
        moneyEarnedScore.setText(decimalFormat.format(moneyEarned) + " DKK");
        money.setText(decimalFormat.format(excessMoney) + " DKK");
        highScore.setText(String.valueOf(highscore));
    }
}
