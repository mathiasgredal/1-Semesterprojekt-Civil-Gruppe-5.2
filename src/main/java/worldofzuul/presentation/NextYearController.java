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
     * Makes an object of the DecimalFormat class and uses it to make double variables decrease the decimalnummer down to two
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

    @FXML
    /**
     * This is the first method that is run
     */
    void initialize() {
        //Chekes if the energy requirement is fulfilled and handles the layout accordingly
        //If "energyBalance" the leftover energy > 0 = succes
        if (energyRequirementIsFulfilled()) {
            statusText.setText("SUCCESS");
            statusText.setTextFill(Color.GREEN);
            btnCon.setText("Continue");
        } else {
            statusText.setText(String.format("REQUIREMENT NOT REACHED: MISSING %.2fkWh", Math.abs(energyBalance())));
            statusText.setTextFill(Color.RED);
            btnCon.setText("Back");
        }

        //Calls the "printYearlyRecap" method so that the player gets the information about his/hers progress
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
    //FIXME: delete if statement - you could use the laodViewHouse method instead
    public void handleCheckNextYearCondition() throws IOException {
        if (energyRequirementIsFulfilled()) {
            printYearlyRecap();
        }

        loadViewHouseScene();
    }

    /**
     * Calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * @throws IOException
     */
    private void loadViewHouseScene() throws IOException {
        GUI_Main.setRoot("view house");
    }

    /**
     * Gets the method Command from the 1st iteration of the game - Commandline Interface (CLI) version.
     *
     * @see worldofzuul.Input.Command#Command(CommandWord, String)
     * @since 1st Iteration
     * @return true is the game is over and false if it is not
     */
    private boolean nextYear() {
        return Game.instance.nextYear(new Command(CommandWord.NEXT, "year"));
    }

    /**
     * Method that shows what the leftover energy requirement is after energy we have recived energy from your own production
     * @return leftover energy requirement (kWH)
     */
    private double energyBalance() {
        return Game.instance.getBuildArea().getYearlyEnergyProduction() - Game.instance.getHouse().getEnergyRequirement();
    }

    /**
     * Method the returens the "energyBalance"/the leftover energy
     * @return true if leftover energy requirement > 0 and false otherwies
     */
    private boolean energyRequirementIsFulfilled() {
        return energyBalance() > 0;
    }

    /**
     * Method that sets the lables text and the information about the players progress
     */
    private void printYearlyRecap() {
        textNextYear1.setText("You are now in the year: " + (2010 + Game.instance.getGameYear()));
        yearlyEmissionText.setText("Your emission for this year is: " + decimalFormat.format(Game.instance.getHouse().getYearlyEmissions()) + " kg CO\u2082");
        totalEmissionText.setText("Your total emission is: " + decimalFormat.format(Game.instance.getPlayer().calculateEmission()) + " kg CO\u2082");
        earnedMoneyText.setText("Your earned money on sold energy: " + decimalFormat.format(Game.instance.getSoldEnergyPrice()) + " DKK");
        balanceText.setText("Your balance are: " + decimalFormat.format(Game.instance.getPlayer().getPlayerEconomy()) + " DKK");
    }

    @FXML
    //FIXME: does this have to be under @FXML and is it used?
    public void handleCheckIfEnd(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("recap");
    }
}
