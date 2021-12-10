package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import worldofzuul.Game;
import worldofzuul.Items.EnergyConsumer.ElectricCar;
import worldofzuul.Items.EnergyConsumer.HeatPump;
import java.io.IOException;

public class ViewHouseController {
    @FXML
    ImageView imageviewPump2, imageviewCar2;

    // Set tooltip for the heater
    private String tooltipDescriptionHeater = String.join("\n",
            String.format("You have a heat pump that runs from electricity"),
            String.format("Yearly energy required: %s kWh", Game.instance.getHouse().getHeater().getYearlyEnergyConsumption()),
            String.format("Yearly cost: %s DKK", Game.instance.getHouse().getHeater().getYearlyCost())
    );

    // Set tooltip for the electric car
    private String tooltipDescriptionCar = String.join("\n",
            String.format("You have a car that runs from electricity"),
            String.format("Yearly energy required: %s kWh", Game.instance.getHouse().getCar().getYearlyEnergyConsumption()),
            String.format("Yearly cost: %s DKK", Game.instance.getHouse().getCar().getYearlyCost())
    );

    @FXML
    private void initialize() {
        if (Game.instance.getHouse().getCar() instanceof ElectricCar) {
            imageviewCar2.setOpacity(1.0);
            Tooltip t1 = new Tooltip(tooltipDescriptionCar);
            Tooltip.install(imageviewCar2, t1);
        }

        if (Game.instance.getHouse().getHeater() instanceof HeatPump) {
            imageviewPump2.setOpacity(1.0);
            Tooltip t2 = new Tooltip(tooltipDescriptionHeater);
            Tooltip.install(imageviewPump2, t2);
        }
    }

    @FXML
    private void handleHouseScene() throws IOException {
        GUI_Main.setRoot("house");
    }

    @FXML
    private void handleNextYear() throws Exception {
        GUI_Main.setRoot("next year");
    }
}
