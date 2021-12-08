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
import worldofzuul.Rooms.Shops.Shop;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class WindturbineShopController {
    @FXML
    private Label windPrice1, windPrice2;
    @FXML
    private Button btnHouse;
    @FXML
    private URL location;

    @FXML
    public void initialize() {
        Shop foundShop = null;
        Label[] arrOfWindPrices = {windPrice1, windPrice2};
        for (int i = 0; i < Game.instance.getShops().size(); i++) {
            if (Game.instance.getShops().get(i).getName().equals(getShopName())) {
                foundShop = Game.instance.getShops().get(i);
            }
        }

        for (int i = 0; i < foundShop.getShopItems().size(); i++) {
            arrOfWindPrices[i].setText("Price: " + foundShop.getShopItem(i).getPrice());
        }
    }

    public void handleBtnHouse(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("house");
    }

    public String getShopName() {
        String[] arrOfLocation = location.getFile().split("/");
        String[] shopName = arrOfLocation[arrOfLocation.length - 1].split("\\.");
        String finalShopName = URLDecoder.decode(shopName[0], StandardCharsets.UTF_8);

        return finalShopName;
    }
}
