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


    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
