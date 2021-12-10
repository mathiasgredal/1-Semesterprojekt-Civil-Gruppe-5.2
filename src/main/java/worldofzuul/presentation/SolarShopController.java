package worldofzuul.presentation;

import javafx.event.ActionEvent;
import java.io.IOException;

public class SolarShopController extends ShopController {
    public void handleBtnHouse(ActionEvent actionEvent) throws IOException {
        GUI_Main.setRoot("house");
    }
}
