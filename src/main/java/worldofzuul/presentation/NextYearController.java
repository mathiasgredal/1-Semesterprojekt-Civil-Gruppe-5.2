package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;

import java.io.IOException;
import java.text.DecimalFormat;

public class NextYearController {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @FXML
    private Button btnCon;
    @FXML
    private Label textNextYear1, yearlyEmissionText, totalEmissionText, earnedMoneyText, balanceText, unableText;

    @FXML
    void initialize() {
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

    /**
     *  Gets the method Command from the 1st iteration of the game - Commandline Interface (CLI) version.
     *
     * @see worldofzuul.Input.Command#Command(CommandWord, String)
     * @since 1st Iteration
     */
    public void handleCheckNextYearCondition() throws IOException {
        if (energyRequirementIsFulfilled()) {
            printYearlyRecap();
        }

        loadViewHouseScene();
    }

    private void loadViewHouseScene() throws IOException {
        GUI_Main.setRoot("view house");
    }

    private boolean energyRequirementIsFulfilled(){
        if (Game.instance.getBuildArea().getYearlyEnergyProduction()  > Game.instance.getHouse().getEnergyRequirement()) {
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
