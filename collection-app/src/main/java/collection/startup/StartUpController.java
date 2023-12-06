package collection.startup;

import collection.usersettings.UserSettings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class StartUpController implements Initializable {

    @FXML
    private Button createNewCollection;

    @FXML
    private Button openCollection;

    @FXML
    private Button exit;

    @FXML
    private ListView<String> recentCollections;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configEvents();
        loadRecentCollections();
    }

    private void loadRecentCollections() {
        final UserSettings userSettings = UserSettings.load();
        if (userSettings.getRecentCollections() != null) {
            recentCollections.getItems().addAll(userSettings.getRecentCollections());
        }
    }

    private void configEvents() {
        exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

}
