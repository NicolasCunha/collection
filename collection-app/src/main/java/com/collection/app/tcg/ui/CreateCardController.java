package com.collection.app.tcg.ui;

import com.google.common.collect.Lists;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 * JavaFX Controller responsible for the screen where creates or edits a card.
 */
public class CreateCardController implements Initializable {

  @FXML
  private TextField game;

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
    this.configureAutoCompletions();
  }

  private void configureAutoCompletions() {
    TextFields.bindAutoCompletion(this.game, "Magic: The Gathering", "Flesh & Blood", "Pokémon");
  }

}
