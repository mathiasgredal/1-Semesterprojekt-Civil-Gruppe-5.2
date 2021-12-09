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
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;

public class SceneController {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @FXML
    ImageView imageviewPump1, imageviewCar1;

    @FXML
    private Button btnHouse, btnHelp, btnNextYear, btnViewHouse;

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
            moneyLabel.setText("Money: " + decimalFormat.format(Game.instance.getPlayer().getPlayerEconomy()) + " DKK");
            emissionLabel.setText("Emission: " + decimalFormat.format(Game.instance.getHouse().getYearlyEmissions()) + " kg CO\u2082");
            energyProductionLabel.setText("Energy production: " + BuildAreaController.humanReadableWattHoursSI(Game.instance.getBuildArea().getYearlyEnergyProduction()));
        }

        if (buildArea != null) {
            this.buildArea.getChildren().add(new BuildGrid(new Point2D(47, 184), 7, 25, 16));
        }

        if (Game.instance.getHouse().getCar() instanceof ElectricCar){
            imageviewCar1.setOpacity(1.0);
        }

        if(Game.instance.getHouse().getHeater() instanceof HeatPump){
            imageviewPump1.setOpacity(1.0);
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

    public void handleBtnViewHouse() throws Exception{
        GUI_Main.setRoot("view house");
    }
}



