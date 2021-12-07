package worldofzuul.presentation;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Rooms.Shops.Shop;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class RetailController {
    @FXML
    private URL location;

    @FXML
    Button btnBuyPump;

    @FXML
    ImageView imageviewPump;


    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }

    public void handleBuyPump(MouseEvent mouseEvent) throws Exception{
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

    public void handleBuyCar(MouseEvent mouseEvent) throws Exception{
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
}
