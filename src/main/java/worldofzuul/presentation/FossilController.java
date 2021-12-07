package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FossilController {
    @FXML
    private void SwitchToFossilShop() throws IOException {
        GUI_Main.setRoot("house");
    }
}
