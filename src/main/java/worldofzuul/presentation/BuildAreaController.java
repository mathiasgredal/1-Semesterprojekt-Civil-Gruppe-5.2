package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Label renewable_label, fossil_label, battery_label;

    @FXML
    private Group buildArea;

    /**
     * Initialize is called after the FXML attributes are saturates
     */
    @FXML
    private void initialize() {
        this.buildArea.getChildren().add(new BuildGrid(new Point2D(18, 36), 20, 27, 17));

        // Set label values
        renewable_label.setText(String.format("Renewable energy: %s pr. year", humanReadableWattHoursSI(Game.instance.getBuildArea().getYearlyEnergyProductionRenewable())));
        fossil_label.setText(String.format("Fossil energy: %s", humanReadableWattHoursSI(Game.instance.getBuildArea().getYearlyEnergyProductionFossil())));
        battery_label.setText(String.format("Battery storage: %s", humanReadableWattHoursSI(Game.instance.getBuildArea().getTotalBatteryCapacity())));
    }

    /**
     * A button to go back to the main scene
     */
    public void handleBtnHouse() throws IOException {
        GUI_Main.setRoot("house");
    }

    /**
     * TODO: Should probably be moved to a seperate helper class
     * TODO: Could be generalized to all units
     * Based on: https://stackoverflow.com/a/3758880
     *
     * @param kWh energy in kWh
     * @return energy in human-readable unit
     */
    private static String humanReadableWattHoursSI(double kWh) {
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
