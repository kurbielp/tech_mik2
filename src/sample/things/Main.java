package sample.things;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Image image = new Image("file:C:\\Users\\dell\\IdeaProjects\\tech_mik2\\src\\sample\\flower.JPG");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setFitWidth(500);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"))
        // ;
        // Group root = new Group();
        // Scene scene = new Scene(root);
        //  scene.setFill(Color.BLACK);

        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));

        //  HBox box = new HBox();
        //  box.getChildren().add(iv1);

        //primaryStage.show();

        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        HBox box = new HBox();
        box.getChildren().add(iv1);
        root.getChildren().add(box);

        stage.setTitle("ImageView");
        stage.setWidth(415);
        stage.setHeight(200);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
