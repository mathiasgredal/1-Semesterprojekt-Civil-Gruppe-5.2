package worldofzuul.presentation;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    //methods for window change
    public void switchToHouse() throws Exception{
        GUI_Main.setRoot("house");
    }

    public void swtichToEnergyShop() throws Exception{
        GUI_Main.setRoot("energyShop");
    }

    public void switchToSolarShop() throws Exception{
        GUI_Main.setRoot("energyShopSolar");
    }

    public void switchToWindShop() throws Exception{
        GUI_Main.setRoot("energyShopWind");
    }

    public void switchToHelp() throws Exception{
        GUI_Main.setRoot("help");
    }

    public void switchToShow() throws Exception{}

    public void switchToBatteryShop() throws Exception{
        GUI_Main.setRoot("batteryShop");
    }

    public void switchToRetailShop() throws Exception{
        GUI_Main.setRoot("retailShop");
    }

    public void switchToEnergyShop() throws IOException{
        GUI_Main.setRoot("energyShop");
    }

    public void switchToFossilShop() throws IOException {
        GUI_Main.setRoot("energyShopFossil");
    }


        /*public void handleBtnEnergyShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShop.fxml")));

        Stage window = (Stage) btnEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnRetailShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/retailShop.fxml")));

        Stage window = (Stage) btnRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }*/

        /*public void handleBtnBuildArea() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/buildArea.fxml")));

        Stage window = (Stage) btnBuildArea.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }*/

    /*public void handelBtnCrossRoad() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/crossRoad.fxml")));

        Stage window = (Stage) btnCrossRoad.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }*/
}