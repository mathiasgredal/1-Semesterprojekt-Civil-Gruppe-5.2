package worldofzuul.presentation;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;

public class FossilController extends ShopController {
    /**
     * This method loads the house.fxml file
     * This method gets run when the "btnHouse" button is pressed in the "Fossil energyShop" scene
     * @throws Exception
     */
    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
