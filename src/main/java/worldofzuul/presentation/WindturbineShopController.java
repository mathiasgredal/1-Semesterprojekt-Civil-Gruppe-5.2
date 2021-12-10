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

public class WindturbineShopController extends ShopController {
    /**
     * Method that calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * This method gets run when the "btnHouse" button is pressed in the "Windturbine shop" scene
     * This method loads the house.fxml file
     * @param actionEvent
     * @throws IOException
     */
    public void handleBtnHouse(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("house");
    }
}
