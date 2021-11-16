package worldofzuul.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI_Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/introduktion.fxml")));
            primaryStage.setTitle("Introduction");
            primaryStage.setScene(new Scene(root, 750, 500));
            primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
