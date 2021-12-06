package worldofzuul.presentation;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.BoundingBox;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import worldofzuul.Items.EnergySource;
import worldofzuul.Items.EnergySourceSize;

import java.util.Objects;

public class BuildItem extends Parent {
    public static final int gridSize = 20;

    private int x, y;
    private int width, height;
    private double deltaX, deltaY;
    private EnergySource source;

    /**
     * Contructor: Loads tooltip, sets width and height, loads textures,
     * finds available position in grid, and installs event handlers to handle dragging
     */
    public BuildItem(EnergySource source) {
        this.source = source;
        this.x = source.getPosX();
        this.y = source.getPosY();

        // Set tooltip for the item
        String tooltipDescription = String.join("\n",
                source.getDescription(),
                String.format("Total generation: %s", BuildAreaController.humanReadableWattHoursSI(source.getTotalGeneratedEnergy())),
                String.format("Total returns: %s kr.", (int) source.getTotalGeneratedMoney()),
                String.format("ROI: %.2f%%", 100 * source.getTotalGeneratedMoney() / source.getPrice())
        );

        Tooltip t = new Tooltip(tooltipDescription);
        Tooltip.install(this, t);

        // If the width or height hasn't been set, then use 1x1 to not break graphics
        if (source.getWidth() == -1 || source.getHeight() == -1) {
            this.width = 1;
            this.height = 1;
        } else {
            this.width = source.getWidth();
            this.height = source.getHeight();
        }

        Rectangle rect = new Rectangle(this.width * gridSize, this.height * gridSize);
        Rectangle rect2 = new Rectangle(this.width * gridSize, this.height * gridSize);
        rect2.setFill(Color.PINK);

        // Load energysource texture
        if (Objects.equals(source.getTextureURL(), "")) {
            rect.setStyle("-fx-background-color: PINK");
            rect.setFill(Color.PINK);
        } else {
            Image img = new Image(getClass().getResource(source.getTextureURL()).toExternalForm());
            rect.setFill(new ImagePattern(img));
        }

        // Add rectangle to node
        getChildren().addAll(rect);

        // If we have a windmill, we will need to add the rotating head.
        // We don't have a good abstraction for this, so it is just hardcoded
        if (source.getName().contains("Wind")) {
            String imageURL = source.getSize() == EnergySourceSize.SMALL ? "/images/Lille mølle hoved.png" : "/images/Stor mølle hoved.png";
            Image img = new Image(getClass().getResource(imageURL).toExternalForm());
            ImageView windMillHead = new ImageView(img);

            // Add proper offset, so the windmill heead is placed on the axel
            if (source.getSize() == EnergySourceSize.SMALL) {
                windMillHead.setTranslateY(-5);
                windMillHead.setTranslateX(-11);
            } else if (source.getSize() == EnergySourceSize.MEDIUM) {
                windMillHead.setScaleX(0.8);
                windMillHead.setScaleY(0.8);
                windMillHead.setTranslateY(-15);
                windMillHead.setTranslateX(-22);
            }

            RotateTransition rt = new RotateTransition(Duration.millis(1500), windMillHead);
            rt.setInterpolator(Interpolator.LINEAR);
            rt.setByAngle(360);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.play();

            getChildren().add(windMillHead);
        }

        // If the source is colliding with an existing energysource, we should find another suitable spot
        parentProperty().addListener(e -> {
            // If the energysource has been moved, then we don't need to give it a position
            if (source.getPosX() != -1 || source.getPosY() != -1)
                return;
            BoundingBox bounds = (BoundingBox) getParent().getUserData();
            outerloop:
            for (int col = 0; col < bounds.getWidth() / gridSize; col++) {
                for (int row = 0; row < bounds.getWidth() / gridSize; row++) {
                    if (setPositionGridFromScene(col * gridSize, row * gridSize)) {
                        // We have found a suitable spot
                        break outerloop;
                    }
                }
            }
        });

        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);

        // Installs the correct event handlers to enable dragging sources around in the grid
        enableDrag();
    }

    /**
     * @param x Grid position
     * @param y Grid position y
     */
    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);
        source.setPosX(this.x);
        source.setPosY(this.y);
    }

    public boolean setPositionGridFromScene(double sceneX, double sceneY) {
        // Save old position, for reverting in case of collision
        int oldX = this.x;
        int oldY = this.y;

        // Calculate new position on grid and apply it
        int newX = (int) Math.round(sceneX / gridSize);
        int newY = (int) Math.round(sceneY / gridSize);
        setPosition(newX, newY);

        // Before doing collision checking, we scale down the rectangle, so we don't get incorrect collision answers
        // when 2 rectangels are side by side(which javafx says are intersecting). The value 0.99 is picked since it
        // is enough to fix javafx collision detection
        setScaleX(0.99);
        setScaleY(0.99);

        // Do collision checking, by looping through each child
        boolean collision = false;
        for (var child : getParent().getChildrenUnmodifiable()) {
            // Skip self
            if (child == this)
                continue;

            // Calculate intersection in parent coordinates
            if (child.getBoundsInParent().intersects(this.getBoundsInParent()))
                collision = true;
        }

        // Check if new position is out of bounds
        var bounds = (BoundingBox) getParent().getUserData();
        if (!sceneToLocal(bounds).contains(getBoundsInLocal()))
            collision = true;

        // Revert scale
        setScaleX(1);
        setScaleY(1);

        // Revert move if there was a collision
        if (collision) {
            setPosition(oldX, oldY);
            return false;
        }

        return true;
    }

    /**
     * This installs the proper event handlers to detect mouse drags and use them to move the object
     */
    private void enableDrag() {
        // Register the start of the drag
        setOnMousePressed(mouseEvent -> {
            deltaX = getTranslateX() - mouseEvent.getSceneX();
            deltaY = getTranslateY() - mouseEvent.getSceneY();
            getScene().setCursor(Cursor.MOVE);
        });

        // Update the position of the object
        setOnMouseDragged(mouseEvent ->
                // This method sets a new position for the object on the grid, if it fits there
                // This prevents objects from being placed on top of each other, and it also makes
                // sure the object is always placed in the grid
                setPositionGridFromScene(mouseEvent.getSceneX() + deltaX, mouseEvent.getSceneY() + deltaY)
        );

        // The following event handlers set the mouse cursor, which tells the user that these items can be moved
        setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));
        setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown())
                getScene().setCursor(Cursor.HAND);
        });
        setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown())
                getScene().setCursor(Cursor.DEFAULT);
        });
    }
}
