package worldofzuul.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;

import java.io.IOException;
import java.text.DecimalFormat;

public class NextYearController {
    /**
     * Makes an instance of the object DecimalFormat class and uses it to make double variables round the decimal number down to two
     **/
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Objects instantiated from the fxml document
     **/
    @FXML
    private Node root;

    @FXML
    private Button btnCon;

    @FXML
    private Label textNextYear1, yearlyEmissionText, totalEmissionText, earnedMoneyText, balanceText, statusText;

    /**
     * This is the first method that is run
     */
    @FXML
    void initialize() {
        //Checks if the energy requirement is fulfilled and handles the layout accordingly
        //If "energyBalance" the leftover energy > 0 = success
        if (energyRequirementIsFulfilled()) {
            statusText.setText("SUCCESS");
            statusText.setTextFill(Color.GREEN);
            btnCon.setText("Continue");
        } else {
            statusText.setText(String.format("REQUIREMENT NOT REACHED: MISSING %.2fkWh", Math.abs(energyBalance())));
            statusText.setTextFill(Color.RED);
            btnCon.setText("Back");
        }

        //Calls the "printYearlyRecap" method so that the player gets the information about the players progress
        printYearlyRecap();

        //Calls the "nextYear" method
        if (nextYear()) {
            // We redirect to recap page, when we have finished the game
            // We can only redirect when this scene is loaded, so we only call redirect when the current scene has been loaded
            root.sceneProperty().addListener(e -> {
                try {
                    GUI_Main.setRoot("recap");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    /**
     * This method gets run when the "continue" button is pressed in the "next year" scene
     * The method calls the "loadViewHouseScene" method
     * @throws IOException
     */
    public void handleCheckNextYearCondition() throws IOException {
        if (energyRequirementIsFulfilled()) {
            printYearlyRecap();
        }

        loadViewHouseScene();
    }

    /**
     * Method that calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * This loads the view house.fxml
     * @throws IOException
     */
    private void loadViewHouseScene() throws IOException {
        GUI_Main.setRoot("view house");
    }

    /**
     * Gets the method Command from the 1st iteration of the game - Commandline Interface (CLI) version.
     *
     * @see worldofzuul.Input.Command#Command(CommandWord, String)
     * @return true is the game is over and false if it is not
     */
    private boolean nextYear() {
        return Game.instance.nextYear(new Command(CommandWord.NEXT, "year"));
    }

    /**
     * Method that shows what the leftover energy requirement is after energy we have received energy from your own production
     * @return leftover energy requirement (kWH)
     */
    private double energyBalance() {
        return Game.instance.getBuildArea().getYearlyEnergyProduction() - Game.instance.getHouse().getEnergyRequirement();
    }

    /**
     * Method the returns the "energyBalance"/the leftover energy
     * @return true if leftover energy requirement > 0 and false otherwise
     */
    private boolean energyRequirementIsFulfilled() {
        return energyBalance() > 0;
    }

    /**
     * Method that sets the lables text and the information about the players progress
     * Uses decimalFormat.format
     */
    private void printYearlyRecap() {
        textNextYear1.setText("You are now in the year: " + (2010 + Game.instance.getGameYear()));
        yearlyEmissionText.setText("Your emission for this year is: " + decimalFormat.format(Game.instance.getHouse().getYearlyEmissions()) + " kg CO\u2082");
        totalEmissionText.setText("Your total emission is: " + decimalFormat.format(Game.instance.getPlayer().calculateEmission()) + " kg CO\u2082");
        earnedMoneyText.setText("Your earned money on sold energy: " + decimalFormat.format(Game.instance.getSoldEnergyPrice()) + " DKK");
        balanceText.setText("Your balance are: " + decimalFormat.format(Game.instance.getPlayer().getPlayerEconomy()) + " DKK");
    }
    
    public void handleCheckIfEnd(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("recap");
    }
}
