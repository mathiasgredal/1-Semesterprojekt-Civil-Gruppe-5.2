package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.net.URL;

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
