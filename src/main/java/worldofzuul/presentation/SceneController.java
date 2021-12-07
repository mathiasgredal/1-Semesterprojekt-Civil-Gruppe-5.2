package worldofzuul.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class SceneController {
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
    private void initialize() {
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

    public void handleBtnNextYear() throws Exception {
        //Change window
        GUI_Main.setRoot("next year");

        /**
         *  Gets the method Command from the 1st iteration of the game - Commandline Interface (CLI) version.
         *
         * @since 1st Iteration
         */
        Command c = new Command(CommandWord.NEXT, "year");
        Game.instance.nextYear(c);
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



