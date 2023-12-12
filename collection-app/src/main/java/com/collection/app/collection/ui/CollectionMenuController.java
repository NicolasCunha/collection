package com.collection.app.collection.ui;

import com.collection.app.CollectionDataHolder;
import com.collection.app.log.LogService;
import com.collection.app.util.StageHolder;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class CollectionMenuController implements Initializable {

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
        stage.setTitle(String.format("Collection - %s", CollectionDataHolder.CURRENT_COLLECTION.getName()));
    }

}
