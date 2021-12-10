package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Items.EnergySource;
import worldofzuul.Rooms.Shops.EnergyShop;
import worldofzuul.Rooms.Shops.Shop;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BatteryShopController extends ShopController {
    //The class inherits from ShopController

    /**
     * Method that calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * This loads the house.fxml
     * @throws IOException
     */
    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
