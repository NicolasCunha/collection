package collection;

import java.io.IOException;

import collection.audit.AuditService;
import collection.core.ui.CheckCreateNewCollection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CollectionApp extends Application {

    private final CheckCreateNewCollection checkCreateNewCollection = new CheckCreateNewCollection();

    @Override
    public void start(Stage stage) throws IOException {
        this.checkCreateNewCollection.checkAnyCollectionExists();
        showMainScreen(stage);
    }

    private void showMainScreen(Stage stage) throws IOException {
        AuditService.audit("Starting application.");
        final FXMLLoader fxmlLoader = new FXMLLoader(CollectionApp.class.getResource("collection-menu.fxml"));
        final Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
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