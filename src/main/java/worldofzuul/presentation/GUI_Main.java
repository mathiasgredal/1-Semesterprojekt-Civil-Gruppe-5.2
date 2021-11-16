package worldofzuul.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUI_Main extends Application {

    public static boolean nextScene = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI_Main_Controller.class.getResource("/worldofzuul.presentation/main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);


    }
}
