module worldofzuul {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.yaml.snakeyaml;
    requires javafx.media;

    opens worldofzuul.presentation to javafx.fxml, javafx.graphics;

    exports worldofzuul.Config to org.yaml.snakeyaml;
    exports worldofzuul.Items to org.yaml.snakeyaml;

    exports worldofzuul.Rooms;
    exports worldofzuul;
}