package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import worldofzuul.Game;

import java.io.IOException;
import java.text.DecimalFormat;

public class NextYearController {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @FXML
    private Button btnCon;
    @FXML
    private Label textNextYear1, yearlyEmissionText, totalEmissionText, earnedMoneyText, balanceText, unableText;

    @FXML
    void initialize(){
        if(energyRequirementIsFulfilled()) {
            textNextYear1.getText();
            yearlyEmissionText.getText();
            totalEmissionText.getText();
            earnedMoneyText.getText();
            balanceText.getText();
            unableText.getText();
            unableText.setVisible(false);
        }
        else{
            unableText.setVisible(true);
            btnCon.setText("Back");
        }

        printYearlyRecap();
    }

    public void handleCheckNextYearCondition() throws IOException {
        if (energyRequirementIsFulfilled()) {
            printYearlyRecap();
        }

        loadHouseScene();
    }

    private void loadHouseScene() throws IOException {
        GUI_Main.setRoot("house");
    }

    private boolean energyRequirementIsFulfilled(){
        if (Game.instance.getBuildArea().getYearlyEnergyProduction() > Game.instance.getHouse().getEnergyRequirement()) {
            return true;
        }

        return false;
    }

    private void printYearlyRecap(){
        textNextYear1.setText("You are now in the year: " + (2010 + Game.instance.getGameYear()));
        yearlyEmissionText.setText("Your emission for this year is: " + decimalFormat.format(Game.instance.getHouse().getYearlyEmissions()) + " kg CO\u2082");
        totalEmissionText.setText("Your total emission is: " + decimalFormat.format(Game.instance.getPlayer().calculateEmission()) + " kg CO\u2082");
        earnedMoneyText.setText("Your earned money on sold energy: " + decimalFormat.format(Game.instance.getSoldEnergyPrice()) + " DKK");
        balanceText.setText("Your balance are: " + decimalFormat.format(Game.instance.getPlayer().getPlayerEconomy()) + " DKK");
    }
}
