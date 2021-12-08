package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import worldofzuul.Game;

import java.io.IOException;
import java.text.DecimalFormat;

public class ViewHouseController {
    @FXML
    private void handleHouseScene() throws IOException {
        GUI_Main.setRoot("house");
    }

    @FXML
    private void handleNextYear() throws Exception{
        GUI_Main.setRoot("next year");
    }

}
