package worldofzuul.presentation;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import worldofzuul.Game;

/**
 * This is a component representing the grid for the build area. It inherits from Group, which means it can be added as
 * a UI component on the node graph in JavaFX.
 * It doesn't support JavaFX built-in layouts, and must be positioned using an offset
 * It supports multiple scales using the grid size
 * It can support multiple grid sizes, and if an element is positioned outside the grid it is cut off
 */
public class BuildGrid extends Group {
    // The offset from the top left corner
    private Point2D offset;

    // Height and width in pixels of a grid cell
    private double gridSize;

    // The size of the grid in cells
    private int gridWidth;
    private int gridHeight;

    // The list of build items. This is an observable list, which is bound to the children list of this class
    // This means that whenever a build item is added to this list, it automatically updates in the UI
    ObservableList<BuildItem> buildItems = FXCollections.observableArrayList();

    /**
     * Constructor for the BuildGrid
     *
     * @param offset     The offset from the top left corner
     * @param gridSize   The size of an individual cell in pixels
     * @param gridWidth  The number of cells wide the build grid will be
     * @param gridHeight The number of cells high the build grid will be
     */
    public BuildGrid(Point2D offset, double gridSize, int gridWidth, int gridHeight) {
        this.offset = offset;
        this.gridSize = gridSize;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        // Offset the component
        this.setTranslateX(this.offset.getX());
        this.setTranslateY(this.offset.getY());

        // This userdata is fetched by the individual build items, which they use during dragging,
        // to avoid going outside the build area
        this.setUserData(getBuildAreaBounds());

        // Load in energysources from build area
        for (var source : Game.instance.getBuildArea().getEnergySources()) {
            // If the energysource is outside the clip area, we clip them off
            if (source.getPosX() + source.getWidth() <= gridWidth && source.getPosY() + source.getHeight() <= gridHeight)
                if (!source.isFossil()) {
                    buildItems.add(new BuildItem(source, getGridSize()));
                }
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
