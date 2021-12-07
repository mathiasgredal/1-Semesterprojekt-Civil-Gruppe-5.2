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
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;

import java.io.IOException;
import java.net.URL;

public class SceneController {
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
    }

    //methods for window change
    public void handleBtnHouse() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnSolarPanelShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Solar shop.fxml")));

        Stage window = (Stage) btnSolarPanelShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnWindturbineShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Windturbine shop.fxml")));

        Stage window = (Stage) btnWindturbineShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnHelp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/help.fxml")));

        Stage window = (Stage) btnHelp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnNextYear() throws Exception {
        //Change window
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/next year.fxml")));

        Stage window = (Stage) btnNextYear.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));

        /**
         *  Gets the method Command from the 1st iteration of the game - Commandline Interface (CLI) version.
         *
         * @since 1st Iteration
         */
        Command c = new Command(CommandWord.NEXT, "year");
        Game.instance.nextYear(c);
    }

    public void handleBtnBatteryShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Battery Shop.fxml")));

        Stage window = (Stage) btnBatteryShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnRetailShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Retail store.fxml")));

        Stage window = (Stage) btnRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnBuildArea() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/buildArea.fxml")));

        Stage window = (Stage) btnBuildArea.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnFossilShop(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Fossil energyshop.fxml")));

        Stage window = (Stage) btnFossilShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}



