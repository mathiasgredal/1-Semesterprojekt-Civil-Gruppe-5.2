package worldofzuul.presentation;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import worldofzuul.Game;

public class BuildGrid extends Group {
    private Point2D offset;

    private double gridSize;
    private int gridWidth;
    private int gridHeight;

    ObservableList<BuildItem> buildItems = FXCollections.observableArrayList();

    public BuildGrid(Point2D offset, double gridSize, int gridWidth, int gridHeight) {
        this.offset = offset;
        this.gridSize = gridSize;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        this.setTranslateX(this.offset.getX());
        this.setTranslateY(this.offset.getY());

        // This userdata is fetched by the individual build items, which they use during dragging,
        // to avoid going outside the build area
        this.setUserData(getBuildAreaBounds());

        // Load in energysources from build area
        for (var source : Game.instance.getBuildArea().getEnergySources()) {
            // If the energysource is outside the clip area, we clip them off
            if (source.getPosX() < gridWidth && source.getPosY() < gridHeight)
                buildItems.add(new BuildItem(source, getGridSize()));
        }

        // Make sure changes in buildItems are reflected in GUI
        Bindings.bindContent(getChildren(), buildItems);
    }

    public double getGridSize() {
        return gridSize;
    }

    /**
     * Creates a bounding box, that describes where energysources are allowed to be placed
     */
    public BoundingBox getBuildAreaBounds() {
        return new BoundingBox(this.offset.getX(), this.offset.getY(), gridWidth * getGridSize(), gridHeight * getGridSize());
    }
}
