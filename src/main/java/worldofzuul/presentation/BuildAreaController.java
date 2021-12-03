package worldofzuul.presentation;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;

public class BuildAreaController {
    @FXML
    private Button btnHouse;
    @FXML
    private Group buildArea;

    ObservableList<BuildItem> buildItems = FXCollections.observableArrayList();

    // Size and position of buildarea
    private Point2D gridOffset = new Point2D(18, 36);
    private int gridWidth = 27;
    private int gridHeight = 17;
    private BoundingBox buildAreaBounds = new BoundingBox(gridOffset.getX(), gridOffset.getY(), gridWidth * BuildItem.gridSize, gridHeight * BuildItem.gridSize);

    /**
     * Initialize is called after the FXML attributes are saturates
     */
    @FXML
    public void initialize() {
        // Setup build area component
        buildArea.setTranslateX(gridOffset.getX());
        buildArea.setTranslateY(gridOffset.getY());
        buildArea.setUserData(buildAreaBounds);

        // Load in energysources from build area
        for (var source : Game.instance.getBuildArea().getEnergySources()) {
            buildItems.add(new BuildItem(source));
        }

        // Make sure changes in buildItems are reflected in GUI
        Bindings.bindContent(buildArea.getChildren(), buildItems);
    }

    /**
     * A button to go back to the main scene
     */
    public void handleBtnHouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}
