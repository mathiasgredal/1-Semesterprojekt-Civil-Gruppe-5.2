package worldofzuul.presentation;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.Pair;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Items.Buyable;
import worldofzuul.Rooms.Shops.Shop;

import java.util.ArrayList;

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

    int x = 0;
    int y = 0;

    @Override
    public void handle(MouseEvent mouseEvent) {
        doButtonBounce();

        // Find the shop in the shop list using the provided shop name
        Shop foundShop = null;
        for (int i = 0; i < Game.instance.getShops().size(); i++) {
            if (Game.instance.getShops().get(i).getName().equals(getShopName())) {
                foundShop = Game.instance.getShops().get(i);
            }
        }

        // Display error if we were unable to find the shop
        if (foundShop == null) {
            Notifications.create()
                    .title("Error")
                    .text("Unknown error")
                    .owner(this)
                    .show();
            return;
        }

        // Get the item
        Buyable item = foundShop.getShopItem(itemIndex - 1);
        if (item == null) {
            showNotification("Error", "Could not find shop item");
            return;
        }

        // Check if we can buy the item
        if (Game.instance.getPlayer().withdrawMoney(item.getPrice())) {
            Game.instance.buyItem(new Command(CommandWord.BUY, Integer.toString(itemIndex)), foundShop);
            playSuccessSound();
            showNotification("Success", "Bought " + item.getName());
        } else {
            // We cannot afford the item
            playErrorSound();
            showNotification("Error", "Cannot afford item");
        }
    }

    // This has to be static, since all buybuttons share the same static notification system
    // There might be another way to do this cleanly without using static, but this works
    private static final ArrayList<Node> notificationsGraphic = new ArrayList<>();
    private final int maxNotifications = 6;

    private void showNotification(String title, String description) {
        // If we have too many notifications we hide the oldest ones, so the don't go outside the window
        if (notificationsGraphic.size() > maxNotifications) {
            var oldestNotification = notificationsGraphic.get(0);
            notificationsGraphic.remove(0);
            oldestNotification.getParent().getParent().setVisible(false);
        }

        // We use an invisible node, to get a reference to the notification graphics, which we then use, to remove it
        // This is a bit of a hack, but it works very well, and the controlsfx notifications class
        // doesn't expose this functionality
        Region n = new Region();

        // Use controlsfx to show a notification
        // We set the owner to this, which makes the notification spawn at the corner of the current window
        // We use css to set the size of the notification
        Notifications.create()
                .title(title)
                .text(description)
                .graphic(n)
                .owner(this)
                .hideAfter(Duration.seconds(2))
                .show();

        notificationsGraphic.add(n);
    }

    private void playErrorSound() {
        Media sound = new Media(getClass().getClassLoader().getResource("errorsound.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    private void playSuccessSound() {
        Media sound = new Media(getClass().getClassLoader().getResource("coinsound.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    private void doButtonBounce() {
        ScaleTransition st = new ScaleTransition(Duration.millis(50), this);
        st.setByX(-0.2f);
        st.setByY(-0.2f);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
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
