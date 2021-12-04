package worldofzuul.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import worldofzuul.Input.CommandWords;
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
    private Button btnHouse, btnHelp;

    @FXML
    private Button btnSolarPanelShop, btnWindturbineShop, btnBatteryShop, btnRetailShop;

    @FXML
    private URL location;

    @FXML
    Label textNextYear1, textNextYear2, textNextYear3, textNextYear4, textNextYear5;

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
        //Change window
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/next year.fxml")));

        Stage window = (Stage) btnNextYear.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));

        //Kan ikke få settext til at virke (det er ikke kun på den her måde jeg har prøvet)
        /*textNextYear1.setText("You are now in the year: " + Game.instance.getGameYear());
        textNextYear2.setText("Your emission for this year is: " + emissions);
        textNextYear3.setText("Your total emission is: " + b.getYearlyEmissions());
        textNextYear4.setText("Your earned money on sold energy: " + soldEnergyPrice);
        textNextYear5.setText("Your balance are: " + p.getPlayerEconomy());
*/
    }

    public void handleBtnBatteryShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/batteryShop.fxml")));

        Stage window = (Stage) btnBatteryShop.getScene().getWindow();
        Stage window = (Stage) btnWindturbineShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnRetailShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Retail store.fxml")));

        Stage window = (Stage) btnRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnBatteryShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Battery Shop.fxml")));

        Stage window = (Stage) btnBatteryShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnHelp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/help.fxml")));

        Stage window = (Stage) btnHelp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnNextYear() throws Exception {
    }

    public void handleBuyItem(MouseEvent mouseEvent) throws Exception {
        String[] arrOfLocation = location.getFile().split("/");
        String[] shopName = arrOfLocation[arrOfLocation.length - 1].split("\\.");
        String finalShopName = URLDecoder.decode(shopName[0], StandardCharsets.UTF_8);


        Shop foundShop = null;
        for (int i = 0; i < Game.instance.getShops().size(); i++) {
            if (Game.instance.getShops().get(i).getName().equals(finalShopName)) {
                foundShop = Game.instance.getShops().get(i);
            }
        }
        if (foundShop != null) {
            Game.instance.buyItem(new Command(CommandWord.BUY, ((ImageView) mouseEvent.getSource()).getId()), foundShop);
        }
    }

    @FXML
    Label labelBuildArea;

    public void handleLabelBuildArea() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/buildArea.fxml")));

        Stage window = (Stage) labelBuildArea.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}



