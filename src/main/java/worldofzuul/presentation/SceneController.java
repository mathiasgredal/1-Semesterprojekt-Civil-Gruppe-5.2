package worldofzuul.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Items.EnergyConsumer.ElectricCar;
import worldofzuul.Items.EnergyConsumer.HeatPump;
import worldofzuul.Items.EnergyConsumer.Heating;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

public class SceneController {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @FXML
    ImageView imageviewPump, imageviewCar;

    @FXML
    private Button btnHouse, btnHelp, btnNextYear;

    @FXML
    private Button btnSolarPanelShop, btnWindturbineShop, btnBatteryShop, btnRetailShop, btnFossilShop, btnBuildArea;

    @FXML
    private URL location;

    @FXML
    private Group buildArea;

    @FXML
    private Label moneyLabel, emissionLabel, energyProductionLabel, yearLabel;

    @FXML
    private void initialize() {
        if(yearLabel != null){
            yearLabel.getText();
            emissionLabel.getText();
            energyProductionLabel.getText();
            moneyLabel.getText();
            yearLabel.setText("Year: " + (2010 + Game.instance.getGameYear()));
            moneyLabel.setText("Money: " + decimalFormat.format(Game.instance.getPlayer().getPlayerEconomy()));
            emissionLabel.setText("Emission: " + decimalFormat.format(Game.instance.getHouse().getYearlyEmissions()));
            energyProductionLabel.setText("Energi production: " + decimalFormat.format(Game.instance.getBuildArea().getYearlyEnergyProduction()));
        }


        if (buildArea != null) {
            this.buildArea.getChildren().add(new BuildGrid(new Point2D(47, 184), 7, 25, 16));
        }

        if (Game.instance.getHouse().getCar() instanceof ElectricCar){
            imageviewCar.setOpacity(1.0);
        }

        if(Game.instance.getHouse().getHeater() instanceof HeatPump){
            imageviewPump.setOpacity(1.0);
        }
    }

    //methods for window change
    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }

    public void handleBtnSolarPanelShop() throws Exception {
        GUI_Main.setRoot("Solar shop");
    }

    public void handleBtnWindturbineShop() throws Exception {
        GUI_Main.setRoot("Windturbine shop");
    }

    public void handleBtnHelp() throws Exception {
        GUI_Main.setRoot("help");
    }

    /**
     *  Gets the method Command from the 1st iteration of the game - Commandline Interface (CLI) version.
     *
     * @see worldofzuul.Input.Command#Command(CommandWord, String) 
     * @since 1st Iteration -
     */
    public void handleBtnNextYear() throws Exception {
        Command c = new Command(CommandWord.NEXT, "year");
        boolean endOfGame = Game.instance.nextYear(c);

        //If 20 years have passed the game is over, and the player will get to the recap
        if(endOfGame) {
            GUI_Main.setRoot("recap");
        }
        else {
            //Change window
            GUI_Main.setRoot("next year");
        }
    }

    public void handleBtnBatteryShop() throws Exception {
        GUI_Main.setRoot("Battery shop");
    }

    public void handleBtnRetailShop() throws Exception {
        GUI_Main.setRoot("Retail store");
    }

    public void handleBtnBuildArea() throws IOException {
      GUI_Main.setRoot("buildArea");
    }

    public void handleBtnFossilShop(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("Fossil energyshop");
    }
}



