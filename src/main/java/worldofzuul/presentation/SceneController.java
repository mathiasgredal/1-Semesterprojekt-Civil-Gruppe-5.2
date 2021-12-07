package worldofzuul.presentation;

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
    private Group buildArea;

    @FXML
    private void initialize() {
        if (buildArea != null) {
            this.buildArea.getChildren().add(new BuildGrid(new Point2D(48, 112), 8, 13, 13));
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



