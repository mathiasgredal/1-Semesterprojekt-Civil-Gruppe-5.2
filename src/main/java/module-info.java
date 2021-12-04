module worldofzuul.presentation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.yaml.snakeyaml;

    opens worldofzuul.presentation to javafx.fxml;
    exports worldofzuul.Config to org.yaml.snakeyaml;
    exports worldofzuul.Items to org.yaml.snakeyaml;
    exports worldofzuul.presentation;
}