package worldofzuul.presentation;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
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


/**
 * This is the universal buy button, which is used the buy items from the different stores
 * It works by calling into the command line interface using Game.instance.buyItem
 * It also adds button press animation and purchase notifications
 * It also has an integrated spinner, which can be used to buy several items at once
 * Since it inherits from a javafx component it can be placed directly inside the fxml
 */
public class BuyButton extends VBox implements EventHandler<MouseEvent> {
    @FXML
    private int itemIndex;

    @FXML
    private String shopName;

    @FXML
    private boolean useSpinner = false;

    // Javafx components
    private Rectangle button;
    private Spinner<Integer> spinner;

    // A configuration variable used to scale the button
    private double scale = 0.7;

    /**
     * The constructor for the buy button
     */
    public BuyButton() {
        // The rectangle is used as the primary display texture for the button
        button = new Rectangle();

        // We install an event handler listning for mouse clicks
        // Since this class implements the eventhandler interface we can just pass that as the handler
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        // Center aligns the button
        setAlignment(Pos.CENTER);
        setSpacing(5);

        // Load button image
        Image img = new Image(getClass().getResource("/images/BuyButton.png").toExternalForm());
        button.setFill(new ImagePattern(img));
        button.setWidth(img.getWidth() * scale);
        button.setHeight(img.getHeight() * scale);

        // Set rounded corners
        button.setArcWidth(10);
        button.setArcHeight(10);

        // Add button rectangle as child
        getChildren().add(button);

        // This listens to when a scene has been added, and uses it to update the money label
        // This will technically update the price label 2 or 3 times
        // It prevents having to do it seperatly on all the stores, although it might not be as clean this way
        sceneProperty().addListener(e -> updateMoneyLabel());
    }

    /**
     * This event handler is run every time the button is clicked
     */
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

        // If there is a spinner, then we use the value from that
        int amountItem = 1;
        if (spinner != null) {
            amountItem = spinner.getValue();
        }

        // Check if we can afford the item
        if (Game.instance.getPlayer().getPlayerEconomy() >= item.getPrice() * amountItem) {
            try {
                // Buy the item the specified number of times
                for (int i = 0; i < amountItem; i++) {
                    Game.instance.buyItem(new Command(CommandWord.BUY, Integer.toString(itemIndex)), foundShop);
                }
                playSuccessSound();
                showNotification("Success", "Bought " + amountItem + "x " + item.getName());

                // Update the money label, we could have made the label an observable value, which would be cleaner than this
                updateMoneyLabel();
            } catch (CannotBuyItemMoreThanOnceException e) {
                // If we have bought the item more than once, display and error
                playErrorSound();
                showNotification("Error", "Cannot buy item more than once");
            }
        } else {
            // We cannot afford the item, display an error
            playErrorSound();
            double missing = Math.abs(Game.instance.getPlayer().getPlayerEconomy() - item.getPrice() * amountItem);
            showNotification("Error", String.format("Cannot afford %sx item\n(missing %.1fDKK)", amountItem, missing));
        }

        // This triggers the button animation
        // We do it at the end, since the notifications can sometimes hang the program,
        // which means that the user doesn't see the button bounce.
        doButtonBounce();
    }

    /**
     * This finds the money label and updates it
     * Using observable values would have been cleaner, but this also works
     */
    private void updateMoneyLabel() {
        var moneyLabel = getParent().lookup("#moneys");
        // Instanceof also covers null checking
        if (moneyLabel instanceof Label) {
            moneyLabel.setLayoutX(500);
            moneyLabel.setLayoutY(25);
            ((Label) moneyLabel).setText(String.format("%.2fDKK", Game.instance.getPlayer().getPlayerEconomy()));
        }
    }

    // This has to be static, since all buybuttons share the same static notification system
    // There might be another way to do this cleanly without using static
    private static final ArrayList<Node> notificationsGraphic = new ArrayList<>();
    private final int maxNotifications = 3;

    /**
     * This displays a notification in the top right corner, that automatically queues down,
     * and disappears after some time. Unless the queue is full in which case they are hidden.
     *
     * @param title       The title of the notification
     * @param description The description of the notification
     */
    private void showNotification(String title, String description) {
        // If we have too many notifications we hide the oldest ones, so they don't go outside the window
        if (notificationsGraphic.size() > maxNotifications) {
            var oldestNotification = notificationsGraphic.get(0);
            notificationsGraphic.remove(0);
            oldestNotification.getParent().getParent().setVisible(false);
            oldestNotification.getParent().getParent().setMouseTransparent(true);
        }

        // We use an invisible node, to get a reference to the notification graphics, which we then use, to remove it
        // This is a bit of a hack, but it works very well, and the controlsfx notifications class
        // doesn't expose this functionality. Region is chosen, since it is invisible.
        Region n = new Region();

        // Use controlsfx to show a notification
        // We set the owner to "this", which makes the notification spawn at the corner of the current window
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

        // Add the notification reference to the list
        notificationsGraphic.add(n);
    }

    /**
     * Loads in the error sound and plays it
     */
    private void playErrorSound() {
        Media sound = new Media(getClass().getClassLoader().getResource("errorsound.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Loads in the success sound and plays it
     */
    private void playSuccessSound() {
        Media sound = new Media(getClass().getClassLoader().getResource("coinsound.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.05);
        mediaPlayer.play();
    }

    /**
     * Does a scale transition, which simulates the press of a physical button
     * When spam pressing a button it shrinks, which is a bug
     */
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

    /**
     * This adds a spinner to the component tree, which is used to buy multiple items
     *
     * @param useSpinner If true, then we add the spinner and if false we don't add a spinner
     */
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
