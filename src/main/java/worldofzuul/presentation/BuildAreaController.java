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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class BuildAreaController {
    @FXML
    private Button btnHouse;

    @FXML
    private Group buildArea;

    @FXML
    private Label renewable_label, fossil_label, battery_label;

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

        // Set label values
        renewable_label.setText(String.format("Renewable energy: %s pr. year", humanReadableWattHoursSI(Game.instance.getBuildArea().getYearlyEnergyProductionRenewable())));
        fossil_label.setText(String.format("Fossil energy: %s", humanReadableWattHoursSI(Game.instance.getBuildArea().getYearlyEnergyProductionFossil())));
        battery_label.setText(String.format("Battery storage: %s", humanReadableWattHoursSI(Game.instance.getBuildArea().getTotalBatteryCapacity())));
    }

    /**
     * A button to go back to the main scene
     */
    public void handleBtnHouse() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/house.fxml")));

        Stage window = (Stage) btnHouse.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    /**
     * TODO: Should probably be moved to a seperate helper class
     * TODO: Could be generalized to all units
     * Based on: https://stackoverflow.com/a/3758880
     *
     * @param kWh energy in kWh
     * @return energy in human-readable unit
     */
    public static String humanReadableWattHoursSI(double kWh) {
        long wH = Math.round(kWh * 1000);

        if (-1000 < wH && wH < 1000) {
            return wH + " Wh";
        }

        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (wH <= -999_950 || wH >= 999_950) {
            wH /= 1000;
            ci.next();
        }
        return String.format("%.1f %cWh", wH / 1000.0, ci.current());
    }
}
