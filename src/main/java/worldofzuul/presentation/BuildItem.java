package worldofzuul.presentation;

import javafx.geometry.BoundingBox;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import worldofzuul.Items.EnergySource;

import java.util.Objects;

public class BuildItem extends Rectangle {
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
        Tooltip t = new Tooltip(source.getDescription());
        Tooltip.install(this, t);

        // Set predefined sizes for the energysource depending on the size of the energysource
        switch (source.getSize()) {
            case SMALL -> {
                this.width = 1;
                this.height = 1;
            }
            case MEDIUM -> {
                this.width = 2;
                this.height = 1;
            }
            case LARGE -> {
                this.width = 3;
                this.height = 1;
            }
        }

        // Load energysource texture
        if (Objects.equals(source.getTextureURL(), "")) {
            setFill(Color.PINK);
        } else {
            Image img = new Image(getClass().getResource(source.getTextureURL()).toExternalForm());
            setFill(new ImagePattern(img));
        }

        setWidth(this.width * gridSize);
        setHeight(this.height * gridSize);

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

    private void enableDrag() {
        setOnMousePressed(mouseEvent -> {
            deltaX = getTranslateX() - mouseEvent.getSceneX();
            deltaY = getTranslateY() - mouseEvent.getSceneY();
            getScene().setCursor(Cursor.MOVE);
        });

        setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));

        setOnMouseDragged(mouseEvent ->
                setPositionGridFromScene(mouseEvent.getSceneX() + deltaX, mouseEvent.getSceneY() + deltaY)
        );

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