package com.collection.app.startup;

import com.collection.app.audit.AuditService;
import com.collection.app.collection.Collection;
import com.collection.app.collection.CollectionService;
import com.collection.app.collection.LoadCollectionAction;
import com.collection.app.usersettings.UserSettings;
import com.collection.app.util.StageHolder;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * JavaFX Controller responsible for the start-up screen, where user
 * can create or load a collection.
 */
public class StartUpController implements Initializable {

  private final CollectionService collectionService = new CollectionService();
  private final LoadCollectionAction loadCollectionAction = new LoadCollectionAction();

  @FXML
  private Button createNewCollection;

  @FXML
  private Button openCollection;

  @FXML
  private Button exit;

  @FXML
  private ListView<Collection> recentCollections;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.configureEvents();
    this.loadRecentCollections();
  }

  private void loadRecentCollections() {
    final UserSettings userSettings = UserSettings.load();
    if (userSettings != null
        && userSettings.getRecentCollections() != null
        && !userSettings.getRecentCollections().isEmpty()) {
      final List<Collection> collectionList =
          this.collectionService.getCollectionsByIds(userSettings.getRecentCollections());
      this.recentCollections.getItems().addAll(collectionList);
    }
  }

  private void configureEvents() {
    this.recentCollections.setOnMousePressed(this::loadCollectionOnDoubleClick);
    this.createNewCollection.setOnAction(event -> this.goToCreateNewCollection());
    this.exit.setOnAction(event -> this.exitApplication());
    this.openCollection.setOnAction(event -> this.openCollection());
    this.checkOpenCollectionEnabled();
  }

  private void loadCollectionOnDoubleClick(final MouseEvent mouseEvent) {
    if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
      final Collection selectedCollection =
          this.recentCollections.getSelectionModel().getSelectedItem();
      this.loadCollectionAction.loadCollection(selectedCollection);
    }
  }

  private void goToCreateNewCollection() {
    StageHolder.closeAndOpen("collection-create-new-collection.fxml",
        "Collection - Create New Collection");
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
