package worldofzuul.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GUI_Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating an image
        Image image = new Image(new FileInputStream("C:\\Users\\Freja Madsen\\Documents\\GitHub\\1-Semesterprojekt-Civil-Gruppe-5.2\\src\\main\\resources\\images\\Himmel.jpg"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);


        Parent root = FXMLLoader.load(getClass().getResource(("/worldofzuul.presentation/introduktion.fxml")));
        primaryStage.setTitle("Green House Jazz");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
