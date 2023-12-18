package com.collection.app.tcg.ui;

import com.collection.app.collection.Collection;
import com.collection.app.tcg.Card;
import com.collection.app.tcg.CardService;
import com.collection.app.util.StageHolder;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * JavaFX Controller responsible for the screen where creates or edits a card.
 */
public class CreateCardController implements Initializable {
  private final CardService cardService = new CardService();

  @FXML
  private TextField game;

  @FXML
  private TextField name;

  @FXML
  private TextField rarity;

  @FXML
  private TextField set;

  @FXML
  private TextField rating;

  @FXML
  private TextField quantity;

  @FXML
  private TextArea comment;

  @FXML
  private Button save;

  @FXML
  private Button saveAndAdd;

  @FXML
  private Button cancel;

  /**
   * Initialize JavaFX controller.
   *
   * @param location  The location used to resolve relative paths for the root object,
   *                  or {@code null} if the location is not known.
   * @param resources The resources used to localize the root object,
   *                  or {@code null} if the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.configureEvents();
  }

  private void configureEvents() {
    Platform.runLater(() -> {
      this.configureAutoCompletions();
      this.configureGameOnChange();
      this.configureButtonEvents();
    });
  }

  private void configureAutoCompletions() {
    final Collection collection = (Collection) this.game.getScene().getWindow().getUserData();
    final List<String> distinctGames = this.cardService.getDistinctGames(collection);
    TextFields.bindAutoCompletion(this.game, distinctGames);
  }

  private void configureGameOnChange() {
    this.game.textProperty().addListener(onChange -> {
      final String game = this.game.getText();
      if (game == null || game.trim().isEmpty()) {
        return;
      }
      final String parsedGame = game.trim();
      final Collection collection = (Collection) this.game.getScene().getWindow().getUserData();
      // Suggest card rarities according to the selected game.
      final List<String> gameRarities = this.cardService.getRarityFromGames(collection, parsedGame);
      TextFields.bindAutoCompletion(this.rarity, gameRarities);

      // Suggest card sets according to the selected game.
      final List<String> sets = this.cardService.getSetsFromGames(collection, parsedGame);
      TextFields.bindAutoCompletion(this.set, sets);
    });
  }

  private void configureButtonEvents() {
    this.cancel.setOnAction(event -> this.goBackToCollectionMenu());
    this.save.setOnAction(event -> {
      this.persistCard();
      this.goBackToCollectionMenu();
    });
    this.saveAndAdd.setOnAction(event -> {
      this.persistCard();
      // TODO add notification to inform user that card has been created
      this.clearFormData();
    });
  }

  private void clearFormData() {
    final TextField[] textFields = new TextField[]{
        this.game,
        this.name,
        this.rarity,
        this.set,
        this.rating,
        this.quantity
    };
    final TextArea[] textAreas = new TextArea[] {this.comment};
    Arrays.stream(textFields).toList().forEach(textField -> textField.setText(""));
    Arrays.stream(textAreas).toList().forEach(textArea -> textArea.setText(""));
  }

  private void persistCard() {
    final Collection collection = (Collection) this.game.getScene().getWindow().getUserData();
    final String game = this.game.getText().trim();
    final String name = this.name.getText().trim();
    final String rarity = this.rarity.getText().trim();
    final String set = this.set.getText().trim();
    final String rating = this.rating.getText().trim();
    final Long quantity = Long.valueOf(this.quantity.getText().trim());
    final String comment = this.comment.getText().trim();

    final Card card = Card.create(
        collection,
        null,
        game,
        name,
        rarity,
        set,
        rating,
        quantity,
        comment
    );

    this.cardService.addCard(card);
  }

  private void goBackToCollectionMenu() {
    final Collection collection = (Collection) this.game.getScene().getWindow().getUserData();
    StageHolder.closeAndOpen("collection-menu.fxml", "Collection - Menu", collection);
  }

}
