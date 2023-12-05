package collection.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public final class DialogUtil {

    private DialogUtil() {
        // noop
    }

    public static boolean confirm(final String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

}
