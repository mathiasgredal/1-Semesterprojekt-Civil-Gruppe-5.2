package worldofzuul.presentation;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This is the component for the individual build item, and must be placed as a child to BuildGrid
 * It displays the given energysource in the grid, and handles movement and it also updates the given energysource
 * with the new position.
 * If the item is positioned at coordinates (-1; -1), then an algorithm places it in a suitable spot
 * This however breaks visually if the grid is filled, since it will just be placed at coordinate (-1;-1)
 * The build item also installs the correct eventhandlers to handle dragging build items around on the grid
 */
public class BuildItem extends Parent {
    // The grid position for the build item
    private int x, y;

    // How many cells wide and tall the build item is
    private int width, height;

    // The size of an individual cell in pixels
    private double gridSize;

    // The offset from when a mouse drag was started
    private double deltaX, deltaY;

    // The energysource for the build item
    private EnergySource source;

    // Subcomponents used in the UI
    private Rectangle rect;
    private ImageView windMillHead;

    /**
     * Contructor: Loads tooltip, sets width and height, loads textures,
     * finds available position in grid, and installs event handlers to handle dragging
     */
    public BuildItem(EnergySource source, double gridSize) {
        this.source = source;
        this.x = source.getPosX();
        this.y = source.getPosY();
        this.gridSize = gridSize;

        // Set tooltip for the item, when mousing over a build item
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

        // Create a rectangle that spans the cell width and height of the energysource
        rect = new Rectangle(this.width * gridSize, this.height * gridSize);

        // Load energysource texture
        if (Objects.equals(source.getTextureURL(), "")) {
            rect.setFill(Color.PINK);
        } else {
            Image img = new Image(getClass().getResource(source.getTextureURL()).toExternalForm());
            rect.setFill(new ImagePattern(img));
        }

        // Add rectangle to node
        getChildren().add(rect);

        // If we have a windmill, we will need to add the rotating head.
        // We don't have a good abstraction for this, so it is just hardcoded
        if (source.getName().contains("Wind")) {
            // Get windmill head texture
            String imageURL = source.getSize() == EnergySourceSize.SMALL ? "/images/Lille mølle hoved.png" : "/images/Stor mølle hoved.png";
            Image img = new Image(getClass().getResource(imageURL).toExternalForm());
            windMillHead = new ImageView(img);

            // Scale head, specifying that the windmill head should be twice as wide as the base
            double scale = (rect.getWidth() * 2.0) / img.getWidth();
            windMillHead.setScaleX(scale);
            windMillHead.setScaleY(scale);

            // Add proper offset, so the windmill heead is placed on the axel
            // We do some number magic, to make sure the head is offset correctly on all scales
            // I just experimented with some offsets and this solution matches exactly on the axel on multiple scales
            double offsetX = -windMillHead.getBoundsInParent().getMinX();
            double offsetY = -windMillHead.getBoundsInParent().getMinY();

            // We hard code some offsets for the different windmill sizes
            if (source.getSize() == EnergySourceSize.SMALL) {
                offsetX -= 10 * scale;
                offsetY -= 5 * scale;
            } else if (source.getSize() == EnergySourceSize.MEDIUM) {
                offsetX -= 16 * scale;
                offsetY -= 12 * scale;
            }

            // We apply the offsets
            windMillHead.setTranslateX(offsetX);
            windMillHead.setTranslateY(offsetY);

            // Initialize rotation animation
            // It does one rotation every 1.5 seconds, which looks visually pleasing,
            // don't know if it is actually correct
            RotateTransition rt = new RotateTransition(Duration.millis(1500), windMillHead);
            // By default, the rotation animation uses a weird animation curve
            // that slows down at the end of the rotation
            rt.setInterpolator(Interpolator.LINEAR);
            rt.setByAngle(360);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.play();

            getChildren().add(windMillHead);
        }

        // If the source is colliding with an existing energysource, we should find another suitable spot
        // We listen to the parent property, since it gives us and event for when a parent has been added.
        // This is useful because we need it the get the bounding box.
        parentProperty().addListener(e -> {
            // If the energysource has been moved, then we don't need to give it a position
            if (source.getPosX() != -1 || source.getPosY() != -1)
                return;
            BoundingBox bounds = (BoundingBox) getParent().getUserData();

            // Loop through all possible positions on the grid and check if a spot is available
            outerloop:
            for (int col = 0; col < bounds.getWidth() / gridSize; col++) {
                for (int row = 0; row < bounds.getWidth() / gridSize; row++) {
                    if (setPositionGridFromScene(col * gridSize, row * gridSize)) {
                        // We have found a suitable spot, we can break of the nested loop
                        break outerloop;
                    }
                }
            }
        });

        // Translate the build item to its grid coordinate
        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);

        // Installs the correct event handlers to enable dragging sources around in the grid
        enableDrag();
    }

    /**
     * Set the position on the grid and apply the move
     *
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

    /**
     * Updates the build item position to a new place in the build grid from scene/screen coordinates
     * It automatically converts the scene coordinates to grid coordinates
     * It checks whether the new position is available and sets the new position if that is the case
     *
     * @param sceneX The pixel x coordinate
     * @param sceneY The pixel y coordinate
     * @return A boolean indicating whether the new position was applied (true if set position was successful, false if there was a collision)
     */
    public boolean setPositionGridFromScene(double sceneX, double sceneY) {
        // Save old position, for reverting in case of collision
        int oldX = this.x;
        int oldY = this.y;

        // Calculate new position on grid and apply it
        int newX = (int) Math.round(sceneX / gridSize);
        int newY = (int) Math.round(sceneY / gridSize);
        setPosition(newX, newY);

        // Before doing collision checking, we scale down the rectangle, so we don't get incorrect collision answers
        // when 2 rectangels are side by side(which javafx says are intersecting). The value 0.75 is picked since it
        // is enough to fix javafx collision detection
        rect.setScaleX(0.75);
        rect.setScaleY(0.75);

        // Do collision checking, by looping through each child
        boolean collision = false;
        for (var child : getParent().getChildrenUnmodifiable()) {
            // Skip self
            if (child == this)
                continue;

            // Since javafx only supports rectilinear convex collision intersection, we need to check all children individually
            List<Bounds> selfBoundingBoxes = new ArrayList<>();
            // A map would be a much better fit, but i am not allowed to use streams :(
            getChildren().forEach(node -> selfBoundingBoxes.add(localToScene(node.getBoundsInParent())));

            List<Bounds> otherBoundingBoxes = new ArrayList<>();
            ((Parent) child).getChildrenUnmodifiable().forEach(node -> otherBoundingBoxes.add(child.localToScene(node.getBoundsInParent())));

            // Compare all children of this object with all children of the other object, and stop if a collision is found
            otherLoop:
            for (var bounds : selfBoundingBoxes) {
                for (var otherBounds : otherBoundingBoxes) {
                    if (bounds.intersects(otherBounds)) {
                        collision = true;
                        break otherLoop;
                    }
                }
            }
        }

        // Check if new position is out of bounds
        var bounds = (BoundingBox) getParent().getUserData();
        if (!bounds.contains(rect.localToScene(rect.getBoundsInLocal())))
            collision = true;

        // Revert scale
        rect.setScaleX(1);
        rect.setScaleY(1);

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
