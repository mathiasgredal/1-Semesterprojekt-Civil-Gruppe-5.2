module worldofzuul.presentation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.yaml.snakeyaml;

    opens worldofzuul.presentation to javafx.fxml;
    exports worldofzuul.presentation;
}