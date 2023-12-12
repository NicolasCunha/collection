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

  /**
   * Defines the root stage.
   *
   * @param stage {@link Stage} instance.
   */
  public static void defineScene(final Stage stage) {
    if (ROOT_STAGE != null) {
      LogService.log("Root Scene is already set.");
      return;
    }
    StageHolder.ROOT_STAGE = stage;
  }

  /**
   * Opens a scene.
   *
   * @param resource {@link String} FXML file.
   * @param appTitle {@link String} Window title.
   * @return {@link Scene} instance.
   */
  public static Scene open(final String resource, final String appTitle) {
    return StageHolder.open(resource, appTitle, null);
  }

  /**
   * Opens a scene.
   *
   * @param resource {@link String} FXML file.
   * @param appTitle {@link String} Window title.
   * @param userData {@link Object} Data transferred to window.
   * @return {@link Scene} instance.
   */
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

  /**
   * Closes current stage.
   */
  public static void close() {
    StageHolder.ROOT_STAGE.close();
  }

  /**
   * Closes current stage and opens with a new scene.
   *
   * @param resource {@link String} FXML file.
   * @param appTitle {@link String} Window title.
   */
  public static void closeAndOpen(final String resource, final String appTitle) {
    StageHolder.closeAndOpen(resource, appTitle, null);
  }

  /**
   * Closes current stage and opens with a new scene.
   *
   * @param resource {@link String} FXML file.
   * @param appTitle {@link String} Window title.
   * @param userData {@link Object} Data transferred to window.
   */
  public static void closeAndOpen(final String resource,
                                  final String appTitle,
                                  final Object userData) {
    StageHolder.close();
    StageHolder.open(resource, appTitle, userData);
  }

  /**
   * Returns current visible scene.
   *
   * @return {@link Scene} instance.
   */
  public static Scene getCurrentScene() {
    return StageHolder.CURRENT_SCENE;
  }

  /**
   * Returns current stage.
   *
   * @return {@link Stage} instance.
   */
  public static Stage getStage() {
    return StageHolder.ROOT_STAGE;
  }

}
