package worldofzuul.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;


public class GUI_Main_Controller {

    @FXML
    private AnchorPane root;

    @FXML
    public void nextScene(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/worldofzuul.presentation/asd.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage) root.getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
