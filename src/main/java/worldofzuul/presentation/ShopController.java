package worldofzuul.presentation;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import worldofzuul.Game;
import worldofzuul.Items.EnergySource;
import worldofzuul.Rooms.Shops.EnergyShop;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public abstract class ShopController {
    @FXML
    private URL location;

    @FXML
    private HBox root;


    @FXML
    private void initialize() {
        //This is the parent class for shops

        boolean useSpinner = root.getUserData() instanceof String && root.getUserData().equals("useSpinner");

        // Get shop
        String shopName = getShopName();
        EnergyShop shop = null;
        for (var element : Game.instance.getShops()) {
            if (element instanceof EnergyShop && Objects.equals(element.getName(), shopName))
                shop = (EnergyShop) element;
        }

        if (shop == null) {
            // Game over!
            return;
        }

        for (int i = 0; i < shop.getShopItems().size(); i++) {
            EnergySource shopItem = shop.getShopItem(i);
            root.getChildren().add(new ShopPane(shopItem, shopName, i + 1, useSpinner));
        }

        // Install event handlers to update the money label
        root.sceneProperty().addListener(e -> updateMoneyLabel());
        root.setOnMouseClicked(e -> updateMoneyLabel());
    }

    /**
     * Gets the shop name, from the FXML file name.
     * It splits the file path, with the .split method.
     *
     * @return The shop name, from the FXML file name
     */
    private String getShopName() {
        String[] arrOfLocation = location.getFile().split("/");
        String[] shopName = arrOfLocation[arrOfLocation.length - 1].split("\\.");
        return URLDecoder.decode(shopName[0], StandardCharsets.UTF_8);
    }

    private void updateMoneyLabel() {
        var moneyLabel = root.getParent().lookup("#moneys");
        // Instanceof also covers null checking
        if (moneyLabel instanceof Label) {
            ((Label) moneyLabel).setText(String.format("%.2fDKK", Game.instance.getPlayer().getPlayerEconomy()));
        }
    }
}
