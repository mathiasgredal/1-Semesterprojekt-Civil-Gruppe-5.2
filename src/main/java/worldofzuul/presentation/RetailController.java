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
import java.io.IOException;
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


    /**
     * Method that calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * This method gets run when the "btnHouse" button is pressed in the "Retail store" scene
     * This loads the house.fxml
     * @throws IOException
     */
    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
