package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import worldofzuul.Game;
import worldofzuul.Items.EnergyConsumer.ElectricCar;
import worldofzuul.Items.EnergyConsumer.HeatPump;

import java.io.IOException;
import java.text.DecimalFormat;

public class ViewHouseController {
    @FXML
    ImageView imageviewPump2, imageviewCar2;

    @FXML
    Label petrol, electric, heatingPump;

    @FXML
    private void initialize() {
        if (Game.instance.getHouse().getCar() instanceof ElectricCar) {
            imageviewCar2.setOpacity(1.0);
            electric.setOpacity(1.0);
            petrol.setOpacity(0.01);
        }

        if (Game.instance.getHouse().getHeater() instanceof HeatPump) {
            imageviewPump2.setOpacity(1.0);
            heatingPump.setOpacity(1.0);
        }

    }

    @FXML
    private void handleHouseScene() throws IOException {
        GUI_Main.setRoot("house");
    }

    @FXML
    private void handleNextYear() throws Exception{
        GUI_Main.setRoot("next year");
    }



}
