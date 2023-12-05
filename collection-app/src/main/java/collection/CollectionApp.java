package collection;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CollectionApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CollectionApp.class.getResource("collection-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Collection");
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}