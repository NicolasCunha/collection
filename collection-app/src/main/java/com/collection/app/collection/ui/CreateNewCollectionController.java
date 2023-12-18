package com.collection.app.collection.ui;

import com.collection.app.collection.Collection;
import com.collection.app.collection.CollectionService;
import com.collection.app.util.StageHolder;
import com.google.common.collect.Lists;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * JavaFX Controller responsible for the screen where user can create a new collection.
 */
public class CreateNewCollectionController implements Initializable {

  private final CollectionService collectionService = new CollectionService();

  @FXML
  private TextField collectionName;

  @FXML
  private CheckBox tcgCards;

  @FXML
  private CheckBox vinyl;

  @FXML
  private CheckBox books;

  @FXML
  private CheckBox games;

  @FXML
  private Button createCollection;

  @FXML
  private Button cancel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.configureEvents();
    this.checkToEnableCreateCollectionButton();
  }

  private void configureEvents() {
    this.collectionName.textProperty()
        .addListener(onChange -> this.checkToEnableCreateCollectionButton());
    Lists.newArrayList(tcgCards, vinyl, books, games)
        .forEach(checkBox -> checkBox.selectedProperty()
            .addListener(onChange -> this.checkToEnableCreateCollectionButton()));
    this.createCollection.setOnAction(event -> this.createCollection());
    this.cancel.setOnAction(event -> this.goToStartUp());
  }

  private void checkToEnableCreateCollectionButton() {
    final String collectionNameTxt = this.collectionName.getText();
    final Boolean tcgCardsSelected = this.tcgCards.isSelected();
    final Boolean vinylSelected = this.vinyl.isSelected();
    final Boolean booksSelected = this.books.isSelected();
    final Boolean gamesSelected = this.games.isSelected();
    this.createCollection.setDisable(!(!collectionNameTxt.isEmpty()
        && (tcgCardsSelected || vinylSelected || booksSelected || gamesSelected)));
  }

  private void createCollection() {
    final String collectionNameTxt = this.collectionName.getText();
    final Boolean tcgCardsSelected = this.tcgCards.isSelected();
    final Boolean vinylSelected = this.vinyl.isSelected();
    final Boolean booksSelected = this.books.isSelected();
    final Boolean gamesSelected = this.games.isSelected();
    final Collection collection = Collection.create(
        collectionNameTxt,
        tcgCardsSelected,
        vinylSelected,
        booksSelected,
        gamesSelected
    );
    this.collectionService.save(collection);
    this.goToCollectionMenu(collection);
  }

  private void goToStartUp() {
    StageHolder.closeAndOpen("collection-startup.fxml", "Collection - Main Menu");
  }

  private void goToCollectionMenu(final Collection collection) {
    StageHolder.closeAndOpen("collection-menu.fxml", "Collection - Menu", collection);
  }

}
