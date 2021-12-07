package worldofzuul.presentation;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Rooms.Shops.Shop;

public class BuyButton extends Rectangle implements EventHandler<MouseEvent> {
    @FXML
    private int itemIndex;

    @FXML
    private String shopName;

    private double scale = 0.7;

    public BuyButton() {
        addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        setTranslateX(100);

        Image img = new Image(getClass().getResource("/images/BuyButton.png").toExternalForm());
        setFill(new ImagePattern(img));

        setWidth(img.getWidth() * scale);
        setHeight(img.getHeight() * scale);

        // Set rounded corners
        setArcWidth(10);
        setArcHeight(10);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Media sound = new Media(getClass().getClassLoader().getResource("coinsound.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
        st.setByX(-0.2f);
        st.setByY(-0.2f);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();

        Shop foundShop = null;
        for (int i = 0; i < Game.instance.getShops().size(); i++) {
            if (Game.instance.getShops().get(i).getName().equals(getShopName())) {
                foundShop = Game.instance.getShops().get(i);
            }
        }

        if (foundShop != null) {
            Game.instance.buyItem(new Command(CommandWord.BUY, Integer.toString(itemIndex)), foundShop);
        }
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }
}
