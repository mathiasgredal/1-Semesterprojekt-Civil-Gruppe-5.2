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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;

public class BuildAreaController {
    @FXML
    private Button btnHouse;
    @FXML
    private Group buildArea;
    @FXML
    private Rectangle background;

    ObservableList<BuildItem> buildItems = FXCollections.observableArrayList();

    // Size and position of buildarea
    private Point2D gridOffset = new Point2D(70, 70);
    private int gridWidth = 20;
    private int gridHeight = 10;
    private BoundingBox buildAreaBounds = new BoundingBox(gridOffset.getX(), gridOffset.getY(), gridWidth * BuildItem.gridSize, gridHeight * BuildItem.gridSize);

    @FXML
    public void initialize() {
        buildArea.setTranslateX(gridOffset.getX());
        buildArea.setTranslateY(gridOffset.getY());
        buildArea.setUserData(buildAreaBounds);

        background.setX(gridOffset.getX());
        background.setY(gridOffset.getY());
        background.setWidth(buildAreaBounds.getWidth());
        background.setHeight(buildAreaBounds.getHeight());
        background.setFill(Color.RED);
        
        // Load in energysources from build area
        for (var source : Game.instance.getBuildArea().getEnergySources()) {
            buildItems.add(new BuildItem(Color.GREEN, source));
        }

        // Make sure changes in buildItems are reflected in GUI
        Bindings.bindContent(buildArea.getChildren(), buildItems);
    }

    public void handleBtnHouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }
}
