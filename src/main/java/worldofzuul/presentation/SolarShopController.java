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

public class SolarShopController {
    @FXML
    private Label solarPrice1, solarPrice2, solarPrice3;
    @FXML
    private Button btnHouse;

    @FXML
    public void initialize() {
        solarPrice1.getText();
        solarPrice2.getText();
        solarPrice3.getText();
        //TODO: Implement the setText() method, get the specific shop and the shops item prices, and write it to the labels.
    }

    public void handleBtnHouse(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}
