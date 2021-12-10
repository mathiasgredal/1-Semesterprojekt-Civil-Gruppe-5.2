package worldofzuul.presentation;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import worldofzuul.Items.EnergySource;

import java.util.Objects;

/**
 * This is the graphics for a shop item in a shop.
 * In the fxml we use an hbox to distribute the shop panes evenly.
 */
public class ShopPane extends AnchorPane {
    /**
     * The constructor for the shop pane.
     *
     * @param item       The energysource to be displayer
     * @param shopName   The shop name that the pane is placed inside of
     * @param index      The index for the shop item, used to call the buy command
     * @param useSpinner Enables the spinner on the buy button
     */
    public ShopPane(EnergySource item, String shopName, int index, boolean useSpinner) {
        addBackgroundImage();
        addTitle(item);
        addThumbNail(item);
        addDescription(item);
        addBuyButton(shopName, index, useSpinner);
    }

    private void addBackgroundImage() {
        Image img = new Image(getClass().getResource("/images/ShopBoxes.png").toExternalForm());
        Rectangle backgroundImage = new Rectangle(148, 304);
        backgroundImage.setFill(new ImagePattern(img));
        getChildren().add(backgroundImage);

        setPrefWidth(backgroundImage.getWidth());
        setPrefHeight(backgroundImage.getWidth());
    }

    private void addTitle(EnergySource item) {
        Label title = new Label(item.getDescription());
        title.setStyle("""
                -fx-text-fill: #ede9e9;
                -fx-font-size: 12;
                -fx-font-weight: bold;
                -fx-wrap-text:true;
                -fx-text-alignment: center;
                """);
        title.setAlignment(Pos.CENTER);
        setLeftAnchor(title, 15.0);
        setRightAnchor(title, 15.0);
        setTopAnchor(title, 13.0);
        getChildren().add(title);
    }

    private void addThumbNail(EnergySource item) {
        Image thumbImg;

        // Use thumbnail if defined otherwise fall back to texture url
        if (!Objects.equals(item.getThumbImageURL(), "")) {
            thumbImg = new Image(getClass().getResource(item.getThumbImageURL()).toExternalForm());
        } else {
            thumbImg = new Image(getClass().getResource(item.getTextureURL()).toExternalForm());
        }
        ImageView thumb = new ImageView(thumbImg);
        thumb.setPreserveRatio(true);

        // This is basically a glorified windmill detector
        if (thumbImg.getWidth() > thumbImg.getHeight()) {
            thumb.setFitHeight(30);
        } else {
            thumb.setFitHeight(90);
        }

        // Wrap in HBox to center image
        HBox wrapper = new HBox();
        wrapper.setAlignment(Pos.CENTER);
        setTopAnchor(wrapper, 45.0);
        setBottomAnchor(wrapper, 150.0);
        setLeftAnchor(wrapper, 20.0);
        setRightAnchor(wrapper, 20.0);
        wrapper.getChildren().add(thumb);
        getChildren().add(wrapper);
    }

    /**
     * This adds the appropriate description to the shop pane, depending on the type of energysource
     */
    private void addDescription(EnergySource item) {
        VBox descriptionArea = new VBox();
        descriptionArea.setAlignment(Pos.BOTTOM_LEFT);
        setLeftAnchor(descriptionArea, 15.0);
        setTopAnchor(descriptionArea, 155.0);
        String descriptionString = "";
        if (item.isBattery()) {
            descriptionString = String.join("\n",
                    String.format("Size: %s", item.getSize().upperCaseName()),
                    String.format("Price: %.0f DKK", item.getPrice()),
                    String.format("Capacity: %.0f kWh", item.getCapacity())
            );
        } else if (item.isRenewable()) {
            descriptionString = String.join("\n",
                    String.format("Size: %s", item.getSize().upperCaseName()),
                    String.format("Emission: %.0f kg CO₂", item.getEmission()),
                    String.format("Output: %.0f kWh", item.getOutput()),
                    String.format("Price: %.0f DKK", item.getPrice())
            );
        } else if (item.isFossil()) {
            descriptionString = String.join("\n",
                    String.format("Emission: %.2f kg CO₂", item.getEmission()),
                    String.format("Output: %.1f kWh", item.getOutput()),
                    String.format("Price: %.2f DKK", item.getPrice())
            );
        }

        Label description = new Label(descriptionString);
        description.setTextFill(Color.web("#ede9e9"));
        descriptionArea.getChildren().add(description);
        getChildren().add(descriptionArea);
    }

    private void addBuyButton(String shopName, int index, boolean useSpinner) {
        BuyButton buy = new BuyButton();
        buy.setUseSpinner(useSpinner);
        buy.setItemIndex(index);
        buy.setShopName(shopName);
        getChildren().add(buy);

        setBottomAnchor(buy, 20.0);
        setLeftAnchor(buy, 0.0);
        setRightAnchor(buy, 0.0);
    }
}
