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
}
