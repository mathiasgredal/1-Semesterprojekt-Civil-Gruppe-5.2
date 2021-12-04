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
    private int gridWidth = 27;
    private int gridHeight = 17;

    ObservableList<BuildItem> buildItems = FXCollections.observableArrayList();

    public BuildGrid(Point2D offset, double gridSize) {
        this.offset = offset;
        this.gridSize = gridSize;

        this.setTranslateX(this.offset.getX());
        this.setTranslateY(this.offset.getY());
        this.setUserData(getBuildAreaBounds());

        // Load in energysources from build area
        for (var source : Game.instance.getBuildArea().getEnergySources()) {
            buildItems.add(new BuildItem(source, getGridSize()));
        }

        // Make sure changes in buildItems are reflected in GUI
        Bindings.bindContent(getChildren(), buildItems);
    }

    public double getGridSize() {
        return gridSize;
    }

    public BoundingBox getBuildAreaBounds() {
        return new BoundingBox(this.offset.getX(), this.offset.getY(), gridWidth * getGridSize(), gridHeight * getGridSize());
    }
}
