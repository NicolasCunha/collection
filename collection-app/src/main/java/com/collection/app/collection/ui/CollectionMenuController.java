package com.collection.app.collection.ui;

import com.collection.app.collection.Collection;
import com.collection.app.log.LogService;
import com.collection.app.util.StageHolder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * JavaFX Controller responsible for the Collection Menu, where user can manage its collections.
 */
public class CollectionMenuController implements Initializable {

  private final Collection currentCollection =
      (Collection) StageHolder.getStage().getProperties().get("current_collection");

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.setWindowTitle();
  }

  private void setWindowTitle() {
    final Stage stage = StageHolder.getStage();
    if (stage == null) {
      LogService.log("Stage reference is null. Something wrong happened!");
      return;
    }
    Platform.runLater(() -> {
      StageHolder.getStage()
          .setTitle(String.format("Collection - %s", currentCollection.getName()));
    });
  }

}
