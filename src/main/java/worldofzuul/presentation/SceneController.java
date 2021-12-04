package worldofzuul.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Player;
import worldofzuul.Rooms.BuildArea;
import worldofzuul.Rooms.House;
import worldofzuul.Rooms.Room;

import java.util.ArrayList;

public class SceneController {
    @FXML
    Button btnHouse, btnBuildArea, btnShopArea, btnWindEnergyShop, btnSolarEnergyShop, btnEnergyShop, btnRetailShop, btnHelp, btnNextYear, btnBatteryShop, btnCon;


    //methods for window change
    public void handleBtnHouse() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnEnergyShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShop.fxml")));

        Stage window = (Stage) btnEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnSolarShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Solar shop.fxml")));

        Stage window = (Stage) btnSolarEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnWindShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Windturbine shop.fxml")));

        Stage window = (Stage) btnWindEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnHelp() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/help.fxml")));

        Stage window = (Stage) btnHelp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }


    public void handleBtnCon() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnCon.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnNextYear() throws Exception {
        //Change window
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/next year.fxml")));

        Stage window = (Stage) btnNextYear.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));

        //Get the method - cormandline interface
        Command c = new Command(CommandWord.NEXT, "year");
        Game.instance.nextYear(c);

        // Is energy requirement is fulfilled?
        BuildArea b = new BuildArea();
        House h = new House(16000);
        Player p = new Player();

        if (b.getYearlyEnergyProduction() > h.getEnergyRequirement()) {
            // Step 0: Are we at 2030
            if (Game.instance.getGameYear() == 20) {
                Game.instance.printRecap();
            }

            // Step 1: Calculate values
            double excessEnergy = b.getYearlyEnergyProduction() - h.getEnergyRequirement();
            double soldEnergyPrice = excessEnergy * b.getEnergySalesPricePrkWh();
            double emissions = b.getYearlyEmissions() + h.getYearlyEmissions();

            // Step 2: Insert yearly salery and energy sales to player balance
            p.insertMoney(soldEnergyPrice + p.getYearlyIncome());
            p.withdrawMoney(h.getYearlyCost());

            // Step 3: Log stuff for recap
            p.transferEnergySources(Game.instance.getGameYear(), b.getEnergySources(), h.getYearlyEmissions());

            // Step 5: Remove fossil fuels
            b.removeFossilEnergySources();

            // Step 6: Add earned money to each energysource
            b.addYearlyEnergyProductionToEnergySources();

            // Step 7: Increment year
            int iy = Game.instance.getGameYear() + 1;
        }


    }

    public void handleBtnBatteryShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/batteryShop.fxml")));

        Stage window = (Stage) btnBatteryShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnRetailShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/retailShop.fxml")));

        Stage window = (Stage) btnRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

        /*public void handleBtnFossilShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShopFossil.fxml")));

        Stage window = (Stage) FossilShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }*/

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

    /*@FXML
    Label lableEnergyShop, lableRetailShop;

    public void handleLableEnergyShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShop.fxml")));

        Stage window = (Stage) lableEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleLableRetailShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/retailShop.fxml")));

        Stage window = (Stage) lableRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }*/
}



