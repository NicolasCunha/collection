package collection;

import java.io.IOException;

import collection.audit.AuditService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CollectionApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        showMainScreen(stage);
    }

    private void showMainScreen(Stage stage) throws IOException {
        AuditService.audit("Starting application.");
        final FXMLLoader fxmlLoader = new FXMLLoader(CollectionApp.class.getResource("collection-startup.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), 550, 450);
        stage.setTitle("Collection");
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
        stage.setOnCloseRequest(closeRequest -> {
            AuditService.audit("Exiting application.");
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }

}