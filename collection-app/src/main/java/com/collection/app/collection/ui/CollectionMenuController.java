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

  /**
   * Initialize JavaFX controller.
   *
   * @param location The location used to resolve relative paths for the root object,
   *                 or {@code null} if the location is not known.
   *
   * @param resources The resources used to localize the root object,
   *                  or {@code null} if the root object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Platform.runLater(this::setWindowTitle);
  }

  private void setWindowTitle() {
    final Collection collection = (Collection) StageHolder.getStage().getUserData();
    final Stage stage = StageHolder.getStage();
    if (stage == null) {
      LogService.log("Stage reference is null. Something wrong happened!");
      return;
    }
    StageHolder.getStage()
        .setTitle(String.format("Collection - %s", collection.getName()));
  }

}
