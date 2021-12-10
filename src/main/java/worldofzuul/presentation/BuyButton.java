package worldofzuul.presentation;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import worldofzuul.Exceptions.CannotBuyItemMoreThanOnceException;
import worldofzuul.Game;
import worldofzuul.Input.Command;
import worldofzuul.Input.CommandWord;
import worldofzuul.Items.Buyable;
import worldofzuul.Rooms.Shops.Shop;

import java.util.ArrayList;

public class BuyButton extends VBox implements EventHandler<MouseEvent> {
    @FXML
    private int itemIndex;

    @FXML
    private String shopName;

    @FXML
    private boolean useSpinner = false;

    private Rectangle button;

    private Spinner<Integer> spinner;

    private double scale = 0.7;

    public BuyButton() {
        button = new Rectangle();
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        setAlignment(Pos.CENTER);

        setSpacing(5);
        Image img = new Image(getClass().getResource("/images/BuyButton.png").toExternalForm());
        button.setFill(new ImagePattern(img));

        button.setWidth(img.getWidth() * scale);
        button.setHeight(img.getHeight() * scale);

        // Set rounded corners
        button.setArcWidth(10);
        button.setArcHeight(10);

        getChildren().add(button);

        // This will technically update the price label 2 or 3 times
        sceneProperty().addListener(e -> updateMoneyLabel());
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Find the shop in the shop list using the provided shop name
        Shop foundShop = null;
        for (int i = 0; i < Game.instance.getShops().size(); i++) {
            if (Game.instance.getShops().get(i).getName().equals(getShopName())) {
                foundShop = Game.instance.getShops().get(i);
            }
        }

        // Display error if we were unable to find the shop
        if (foundShop == null) {
            showNotification("Error", "Unknown error");
            return;
        }

        // Get the item
        Buyable item = foundShop.getShopItem(itemIndex - 1);
        if (item == null) {
            showNotification("Error", "Could not find shop item");
            return;
        }

        // Check if we can buy the item
        int amountItem = 1;
        if (spinner != null) {
            amountItem = spinner.getValue();
        }
        if (Game.instance.getPlayer().getPlayerEconomy() >= item.getPrice() * amountItem) {
            try {
                for (int i = 0; i < amountItem; i++) {
                    Game.instance.buyItem(new Command(CommandWord.BUY, Integer.toString(itemIndex)), foundShop);
                }
                playSuccessSound();
                showNotification("Success", "Bought " + amountItem + "x " + item.getName());

                // It would be better to use polymorphism to handle this.
                updateMoneyLabel();
            } catch (CannotBuyItemMoreThanOnceException e) {
                playErrorSound();
                showNotification("Error", "Cannot buy item more than once");
            }
        } else {
            // We cannot afford the item
            playErrorSound();
            double missing = Math.abs(Game.instance.getPlayer().getPlayerEconomy() - item.getPrice() * amountItem);
            showNotification("Error", String.format("Cannot afford %sx item\n(missing %.1fDKK)", amountItem, missing));
        }
        doButtonBounce();
    }

    private void updateMoneyLabel() {
        var moneyLabel = getParent().lookup("#moneys");
        moneyLabel.setLayoutX(500);
        moneyLabel.setLayoutY(25);
        // Instanceof also covers null checking
        if (moneyLabel instanceof Label) {
            ((Label) moneyLabel).setText(String.format("%.2fDKK", Game.instance.getPlayer().getPlayerEconomy()));
        }
    }

    // This has to be static, since all buybuttons share the same static notification system
    // There might be another way to do this cleanly without using static, but this works
    private static final ArrayList<Node> notificationsGraphic = new ArrayList<>();
    private final int maxNotifications = 3;

    private void showNotification(String title, String description) {
        // If we have too many notifications we hide the oldest ones, so the don't go outside the window
        if (notificationsGraphic.size() > maxNotifications) {
            var oldestNotification = notificationsGraphic.get(0);
            notificationsGraphic.remove(0);
            oldestNotification.getParent().getParent().setVisible(false);
            oldestNotification.getParent().getParent().setMouseTransparent(true);
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
                .position(Pos.TOP_RIGHT)
                .hideAfter(Duration.seconds(2))
                .show();

        // Do a y-offset, so the notification doesn't collide with the window border
        n.getParent().getParent().getParent().setLayoutY(20);

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
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();
    }

    private void doButtonBounce() {
        ScaleTransition st = new ScaleTransition(Duration.millis(50), this.button);
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

    public boolean isUseSpinner() {
        return useSpinner;
    }

    public void setUseSpinner(boolean useSpinner) {
        if (useSpinner) {
            this.spinner = new Spinner<>(1, 500, 0);
            this.spinner.setPrefWidth(button.getWidth());
            this.spinner.setEditable(true);
            getChildren().add(0, spinner);
        }
        this.useSpinner = useSpinner;
    }
}
