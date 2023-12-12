package com.collection.app.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Utility class to show modal JavaFX dialogs to user.
 */
public final class DialogUtil {

  private DialogUtil() {
    // noop
  }

  /**
   * Confirm something with user.
   *
   * @param message {@link String} dialog message.
   * @return {@link Boolean} if user clicked on 'Yes'.
   */
  public static boolean confirm(final String message) {
    final Alert alert =
        new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
    alert.setHeaderText(null);
    alert.showAndWait();
    return alert.getResult() == ButtonType.YES;
  }

}
