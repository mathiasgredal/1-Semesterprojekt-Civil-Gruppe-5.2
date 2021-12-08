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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Rooms.Shops.Shop;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Player;
import worldofzuul.Rooms.BuildArea;
import worldofzuul.Rooms.House;
import worldofzuul.Rooms.Room;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SceneController {
    @FXML
    private Button btnHouse, btnHelp, btnNextYear;

    @FXML
    private Button btnSolarPanelShop, btnWindturbineShop, btnBatteryShop, btnRetailShop, btnFossilShop;

    @FXML
    private URL location;

    @FXML
    private Group buildArea;

    @FXML
    private void initialize() {
        if (buildArea != null) {
            this.buildArea.getChildren().add(new BuildGrid(new Point2D(48, 112), 8, 13, 13));
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
        //If 20 years have passed the game is over, and the player will get to the recap
        if(Game.instance.getGameYear() == 20) {
            GUI_Main.setRoot("recap");
        }
        else {
            //Change window
            GUI_Main.setRoot("next year");

            Command c = new Command(CommandWord.NEXT, "year");
            Game.instance.nextYear(c);
        }
    }

    public void handleBtnBatteryShop() throws Exception {
        GUI_Main.setRoot("Battery shop");
    }

    public void handleBtnRetailShop() throws Exception {
        GUI_Main.setRoot("Retail store");
    }

    @FXML
    Label labelBuildArea;

    public void handleLabelBuildArea() throws IOException {
        GUI_Main.setRoot("buildArea");
    }

    public void handleBtnFossilShop(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("Fossil energyshop");
    }
}



