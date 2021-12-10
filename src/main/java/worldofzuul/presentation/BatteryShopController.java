package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Items.EnergySource;
import worldofzuul.Rooms.Shops.EnergyShop;
import worldofzuul.Rooms.Shops.Shop;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BatteryShopController extends ShopController {
    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
