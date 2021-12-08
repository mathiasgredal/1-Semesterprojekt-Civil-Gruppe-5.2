package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import worldofzuul.Game;

import java.text.DecimalFormat;

public class RecapController {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @FXML
    private Label emissionScore, moneyEarnedScore, money, highScore;

    @FXML
    public void initialize(){
        double emission = Game.instance.getPlayer().calculateTotalEmission();
        double moneyEarned = Game.instance.getSoldEnergyPrice();
        double excessMoney = Game.instance.getPlayer().getPlayerEconomy();
        int highscore = (int)((50000/(emission/1000))+moneyEarned+excessMoney);


        emissionScore.setText(decimalFormat.format(emission) + " kg CO\u2082");
        moneyEarnedScore.setText(decimalFormat.format(moneyEarned) + " DKK");
        money.setText(decimalFormat.format(excessMoney) + " DKK");
        highScore.setText(String.valueOf(highscore));

    }
}
