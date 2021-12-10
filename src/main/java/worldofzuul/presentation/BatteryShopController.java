package worldofzuul.presentation;

public class BatteryShopController extends ShopController {
    //The class inherits from ShopController

    /**
     * Method that calls the "setRoot" method from "GUI_Main" which is used to load the object hierarchy from an XML document (or in other terms change scenes)
     * This method gets run when the "btnHouse" button is pressed in the "Battery shop" scene
     * This loads house.fxml
     * @throws IOException
     */
    public void handleBtnHouse() throws Exception {
        GUI_Main.setRoot("house");
    }
}
