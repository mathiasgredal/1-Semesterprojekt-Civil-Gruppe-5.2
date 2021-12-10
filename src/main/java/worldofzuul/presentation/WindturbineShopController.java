package worldofzuul.presentation;

import javafx.event.ActionEvent;
import java.io.IOException;

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
