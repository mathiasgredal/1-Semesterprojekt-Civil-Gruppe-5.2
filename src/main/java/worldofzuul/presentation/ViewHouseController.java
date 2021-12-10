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
        /**
         * If house has an Electric, update the car tooltip and the car image
         */
        if (Game.instance.getHouse().getCar() instanceof ElectricCar) {
            imageviewCar2.setOpacity(1.0);
            Tooltip t1 = new Tooltip(tooltipDescriptionCar);
            Tooltip.install(imageviewCar2, t1);
        }

        /**
         * If house has a HeatPump, up date the heatpump tooltip and the add the heatpump image to the house
         */
        if (Game.instance.getHouse().getHeater() instanceof HeatPump) {
            imageviewPump2.setOpacity(1.0);
            Tooltip t2 = new Tooltip(tooltipDescriptionHeater);
            Tooltip.install(imageviewPump2, t2);
        }
    }

    /**
     * Method that calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * This method gets run when the "btnHouse" button is pressed in the "Retail store" scene
     * This method loads the house.fxml file
     * @throws IOException
     */
    @FXML
    private void handleHouseScene() throws IOException {
        GUI_Main.setRoot("house");
    }

    /**
     * This method loads the next year.fxml file
     * @throws Exception
     */
    @FXML
    private void handleNextYear() throws Exception {
        GUI_Main.setRoot("next year");
    }
}
