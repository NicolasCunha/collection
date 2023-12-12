package com.collection.app.util;

import com.collection.app.audit.AuditService;
import com.collection.app.log.LogService;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Utility class to share between the system the JavaFX {@link Scene scenes} and
 * {@link Stage stages} that are currently opened.
 *
 * <p>This class is most likely a design flaw and should be revisited in the future.
 *
 * <p>TODO: Remove StageHolder usage and use normal FXML screen closing/opening/data transfer.
 */
public final class StageHolder {

  private static Stage ROOT_STAGE = null;
  private static Scene CURRENT_SCENE = null;

  private StageHolder() {
    // no op
  }

  public static void defineScene(final Stage stage) {
    if (ROOT_STAGE != null) {
      LogService.log("Root Scene is already set.");
      return;
    }
    StageHolder.ROOT_STAGE = stage;
  }

  public static Scene open(final String resource, final String appTitle) {
    return StageHolder.open(resource, appTitle, null);
  }

  public static Scene open(final String resource, final String appTitle, final Object userData) {
    final FXMLLoader fxmlLoader = new FXMLLoader(ResourceLoader.load(resource));
    try {
      final Scene scene = new Scene(fxmlLoader.load());
      ROOT_STAGE.setTitle(appTitle);
      ROOT_STAGE.setScene(scene);
      scene.getWindow().sizeToScene();
      if (userData != null) {
        ROOT_STAGE.setUserData(userData);
      }
      ROOT_STAGE.show();
      ROOT_STAGE.setOnCloseRequest(closeRequest -> {
        ROOT_STAGE.close();
        AuditService.audit("Exiting application.");
        Platform.exit();
        System.exit(0);
      });
      StageHolder.CURRENT_SCENE = scene;
      return scene;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void close() {
    StageHolder.ROOT_STAGE.close();
  }

  public static void closeAndOpen(final String resource, final String appTitle) {
    StageHolder.closeAndOpen(resource, appTitle, null);
  }

  public static void closeAndOpen(final String resource, final String appTitle,
                                  final Object userData) {
    StageHolder.close();
    StageHolder.open(resource, appTitle, userData);
  }

  public static Scene getCurrentScene() {
    return StageHolder.CURRENT_SCENE;
  }

  public static Stage getStage() {
    return StageHolder.ROOT_STAGE;
  }

}
