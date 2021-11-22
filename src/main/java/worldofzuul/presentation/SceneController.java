package worldofzuul.presentation;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneController {
    @FXML
    Button btnHouse, btnBuildArea, btnCrossRoad, btnShopArea, btnEnergyShop, btnRetailShop;

    //methods
    public void handelBtnHouse() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    public void handelBtnBuildArea() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/buildArea.fxml")));

        Stage window = (Stage) btnBuildArea.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    public void handelBtnCrossRoad() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/crossRoad.fxml")));

        Stage window = (Stage) btnCrossRoad.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    public void handelBtnShopArea() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/shopArea.fxml")));

        Stage window = (Stage) btnShopArea.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    public void handelBtnEnergyShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/energyShop.fxml")));

        Stage window = (Stage) btnEnergyShop.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    public void handelBtnRetailShop() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/retailShop.fxml")));

        Stage window = (Stage) btnRetailShop.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
    }

    /*@FXML
    //Images
    private ImageView skyImage;


    public void handleImageSky(URL url, ResourceBundle rb) {
        Image sky = new Image(getClass().getResourceAsStream("scr/images/Himmel.jpg"));
        skyImage.setImage(sky);
    }

     */
}
