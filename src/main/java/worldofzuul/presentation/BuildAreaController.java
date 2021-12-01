package worldofzuul.presentation;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Items.EnergySource;

import java.io.IOException;
import java.util.List;

public class BuildAreaController {
    @FXML
    private Button btnHouse;

    @FXML
    private Group buildArea;

    ObservableList<BuildItem> buildItems = FXCollections.observableArrayList(List.of(
            new BuildItem(0, 0, 1, 1, Color.RED),
            new BuildItem(1, 1, 1, 1, Color.GREEN)
    ));

    @FXML
    public void initialize() {
        // Make sure changes in buildItems are reflected in GUI
        buildArea.setManaged(false);
        Bindings.bindContent(buildArea.getChildren(), buildItems);
        Game.instance.getBuildArea().getEnergySources().addListener((ListChangeListener<? super EnergySource>) event -> {
            buildItems.clear();

            for (var item : event.getList()) {
                buildItems.add(new BuildItem(0, 0, item.getWidth(), item.getHeight(), Color.GREEN));
            }
        });
    }

    public void handleBtnHouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void handleAddItem(ActionEvent actionEvent) {
        buildItems.add(new BuildItem(0, 0, 2, 2, Color.ORANGE));
//        buildItems.add(new BuildItem(1, 4, 1, 1, Color.BLUE));
    }
}
