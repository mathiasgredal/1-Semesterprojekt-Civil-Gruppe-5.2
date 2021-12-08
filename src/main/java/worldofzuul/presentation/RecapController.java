package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import worldofzuul.Game;

public class RecapController {

    @FXML
    private Label emissionScore, moneyEarnedScore, money, highScore;

    @FXML
    public void initialize(){
        int emission = Game.instance.getPlayer().calculateTotalEmission();
        int moneyEarned = (int)Game.instance.getSoldEnergyPrice();
        int excessMoney = (int)Game.instance.getPlayer().getPlayerEconomy();
        int highscore = (50000/(emission/1000))+moneyEarned+excessMoney;


        emissionScore.setText(String.valueOf(emission));
        moneyEarnedScore.setText(String.valueOf(moneyEarned));
        money.setText(String.valueOf(excessMoney));
        highScore.setText(String.valueOf(highscore));

    }
}
