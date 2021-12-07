package worldofzuul.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI_Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/introduction.fxml")));
        primaryStage.setTitle("Green House Jazz");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    //Sets the root FXML
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUI_Main.class.getResource("/worldofzuul.presentation/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
