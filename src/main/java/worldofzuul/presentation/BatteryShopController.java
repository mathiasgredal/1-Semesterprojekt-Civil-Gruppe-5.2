package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Items.EnergySource;
import worldofzuul.Rooms.Shops.Shop;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class BatteryShopController {
    @FXML
    private URL location;

    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
