package com.collection.app.startup;

import com.collection.app.audit.AuditService;
import com.collection.app.collection.CollectionService;
import com.collection.app.usersettings.UserSettings;
import com.collection.app.util.ResourceLoader;
import com.collection.app.util.StageHolder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartUpController implements Initializable {

    private final CollectionService collectionService = new CollectionService();

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
        this.configureEvents();
        this.loadRecentCollections();
    }

    private void loadRecentCollections() {
        final UserSettings userSettings = UserSettings.load();
        if (userSettings != null && userSettings.getRecentCollections() != null) {
            this.recentCollections.getItems().addAll(userSettings.getRecentCollections());
        }
    }

    private void configureEvents() {
        this.createNewCollection.setOnAction(event -> this.goToCreateNewCollection());
        this.exit.setOnAction(event -> this.exitApplication());
        this.openCollection.setOnAction(event -> this.openCollection());
        this.checkOpenCollectionEnabled();
    }

    private void goToCreateNewCollection() {
        final Stage stage = (Stage) this.createNewCollection.getScene().getWindow();
        stage.close();
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(ResourceLoader.load("collection-create-new-collection.fxml"));
            final Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkOpenCollectionEnabled() {
        this.openCollection.setDisable(!this.collectionService.existsAnyCollection());
    }

    private void openCollection() {
        StageHolder.closeAndOpen("collection-load-collection.fxml", "Collection - Load Collection");
    }

    private void exitApplication() {
        AuditService.audit("Exiting application.");
        ((Stage) this.createNewCollection.getScene().getWindow()).close();
        Platform.exit();
        System.exit(0);
    }

}
