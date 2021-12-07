package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;

public class NextYearController {

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

            printYearlyRecap();
        }
        else{
            unableText.setVisible(true);
            btnCon.setText("Back");
        }
    }

    public void handleCheckNextYearCondition() throws IOException {
        if (energyRequirementIsFulfilled()) {
            printYearlyRecap();
        }

        loadHouseScene();
    }

    private void loadHouseScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));
        Stage window = (Stage) btnCon.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    private boolean energyRequirementIsFulfilled(){
        if (Game.instance.getBuildArea().getYearlyEnergyProduction() > Game.instance.getHouse().getEnergyRequirement()) {
            return true;
        }

        return false;
    }

    private void printYearlyRecap(){
        textNextYear1.setText("You are now in the year: " + Game.instance.getGameYear());
        yearlyEmissionText.setText("Your emission for this year is: " + Game.instance.getHouse().getYearlyEmissions());
        totalEmissionText.setText("Your total emission is: " + Game.instance.getPlayer().calculateTotalEmission());
        earnedMoneyText.setText("Your earned money on sold energy: " + Game.instance.getSoldEnergyPrice());
        balanceText.setText("Your balance are: " + Game.instance.getPlayer().getPlayerEconomy());
    }
}
