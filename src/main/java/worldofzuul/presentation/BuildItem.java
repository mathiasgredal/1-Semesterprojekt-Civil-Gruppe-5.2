package worldofzuul.presentation;

import javafx.geometry.BoundingBox;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import worldofzuul.Items.EnergySource;

public class BuildItem extends Rectangle {
    int x, y;
    int width, height;
    double deltaX, deltaY;

    public static final int gridSize = 20;

    EnergySource source;


    public BuildItem(Color color, EnergySource source) {
        this.x = source.getPosX();
        this.y = source.getPosY();

        Tooltip t = new Tooltip(source.getDescription());
        Tooltip.install(this, t);

        switch (source.getSize()) {
            case SMALL -> {
                this.width = 1;
                this.height = 1;
                Image img = new Image(getClass().getResource("/images/LilleSolcelle.png").toExternalForm());
                setFill(new ImagePattern(img));
            }
            case MEDIUM -> {
                this.width = 2;
                this.height = 1;
                Image img = new Image(getClass().getResource("/images/MellemSolcelle.png").toExternalForm());
                setFill(new ImagePattern(img));
            }
            case LARGE -> {
                this.width = 3;
                this.height = 1;
                Image img = new Image(getClass().getResource("/images/StoreSolcelle.png").toExternalForm());
                setFill(new ImagePattern(img));

            }
        }

        this.source = source;


        setWidth(this.width * gridSize);
        setHeight(this.height * gridSize);

        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);

        enableDrag();
    }

    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setTranslateX(this.x * gridSize);
        setTranslateY(this.y * gridSize);
        source.setPosX(this.x);
        source.setPosY(this.y);
    }

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
