package com.collection.app.collection.ui;

import com.collection.app.audit.AuditService;
import com.collection.app.collection.Collection;
import com.collection.app.collection.CollectionService;
import com.collection.app.collection.LoadCollectionAction;
import com.collection.app.util.StageHolder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * JavaFX Controller responsible for the screen where user can create open and load a collection.
 */
public class LoadCollectionMenuController implements Initializable {

  private final CollectionService collectionService = new CollectionService();
  private final LoadCollectionAction loadCollectionAction = new LoadCollectionAction();

  @FXML
  private ComboBox<Collection> collections;

  @FXML
  private Button cancel;

  @FXML
  private Button loadCollection;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.configureEvents();
    this.loadAvailableCollections();
    this.checkLoadCollectionEnabled();
  }

  private void configureEvents() {
    this.collections.valueProperty().addListener(onChange -> this.checkLoadCollectionEnabled());
    this.loadCollection.setOnAction(event -> this.loadCollection());
    this.cancel.setOnAction(event -> this.goBackToStartUp());
  }

  private void loadAvailableCollections() {
    this.collections.getItems().addAll(this.collectionService.getCollections());
    if (!this.collections.getItems().isEmpty()) {
      this.collections.setValue(this.collections.getItems().get(0));
    }
  }

  private void checkLoadCollectionEnabled() {
    this.loadCollection.setDisable(this.collections.getSelectionModel().getSelectedItem() == null);
  }

  private void loadCollection() {
    final Collection selectedCollection = this.collections.getSelectionModel().getSelectedItem();
    this.loadCollectionAction.loadCollection(selectedCollection);
  }

  private void goBackToStartUp() {
    AuditService.audit("Closing load collection. Going back to start up menu.");
    StageHolder.closeAndOpen("collection-startup.fxml", "Collection - Main Menu");
  }

}
