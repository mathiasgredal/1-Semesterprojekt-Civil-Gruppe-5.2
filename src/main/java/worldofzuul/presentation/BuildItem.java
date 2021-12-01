package worldofzuul.presentation;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BuildItem extends Rectangle {
    int x, y;
    int width, height;
    double deltaX, deltaY;

    final int gridSize = 20;


    public BuildItem(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setFill(color);

        setWidth(width * gridSize);
        setHeight(height * gridSize);

        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);

        enableDrag();
    }

    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);
    }

    /**
     *
     */
    public void setPositionGridFromScene(double sceneX, double sceneY) {
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

        // Revert scale
        setScaleX(1);
        setScaleY(1);

        // Revert move if there was a collision
        if (collision) {
            setPosition(oldX, oldY);
        }
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
