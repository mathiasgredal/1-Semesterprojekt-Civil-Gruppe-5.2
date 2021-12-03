package worldofzuul.presentation;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Input.CommandWords;
import worldofzuul.Rooms.Shops.Shop;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SceneController {
    @FXML
    Button btnHouse, btnBuildArea, btnShopArea, btnWindEnergyShop, btnSolarEnergyShop, btnEnergyShop, btnRetailShop, btnHelp, btnShow;

    @FXML
    private URL location;


    //methods for window change
    public void handleBtnHouse() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

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

    /*public void handleBtnEnergyShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShop.fxml")));

        Stage window = (Stage) btnEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        Game.instance.goRoom(new Command(CommandWord.GO, "south"));
    }*/

    public void handleBtnFossilShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShopFossil.fxml")));

        Stage window = (Stage) FossilShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnSolarPanelShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Solar shop.fxml")));

        Stage window = (Stage) btnSolarPanelShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnWindturbineShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Windturbine shop.fxml")));

        Stage window = (Stage) btnWindturbineShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnRetailShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Retail store.fxml")));

        Stage window = (Stage) btnRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnBatteryShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/Battery Shop.fxml")));

        Stage window = (Stage) btnBatteryShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
    }

    public void handleBtnHelp() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/help.fxml")));

        Stage window = (Stage) btnHelp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleBtnNextYear() throws Exception{}

    public void handleBuyItem(MouseEvent mouseEvent) throws Exception{
        String[] arrOfLocation = location.getFile().split("/");
        String[] shopName = arrOfLocation[arrOfLocation.length - 1].split("\\.");
        String finalShopName = URLDecoder.decode(shopName[0], StandardCharsets.UTF_8);


        Shop foundShop = null;
        for(int i = 0; i < Game.instance.getShops().size(); i++){
            if(Game.instance.getShops().get(i).getName().equals(finalShopName)){
                foundShop = Game.instance.getShops().get(i);
            }
        }
        if(foundShop != null){
            Game.instance.buyItem(new Command(CommandWord.BUY, ((ImageView) mouseEvent.getSource()).getId()), foundShop);
        }
    }

    @FXML
    Label lableEnergyShop, lableRetailShop, labelBuildArea;

    public void handleLabelEnergyShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShop.fxml")));

        Stage window = (Stage) lableEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleLabelRetailShop() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/retailShop.fxml")));

        Stage window = (Stage) lableRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleLabelBuildArea() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/buildArea.fxml")));

        Stage window = (Stage) labelBuildArea.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}



