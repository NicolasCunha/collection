package com.collection.app.util;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public final class TableViewUtil {

    private TableViewUtil() {
        // noop
    }

    public static void configTableCellValueFactory(final TableView<?>... tables) {

        for(final TableView<?> table : tables) {
            table.getColumns().forEach(column -> {
                // Ids and class properties are mapped to be equal.
                column.setCellValueFactory(new PropertyValueFactory<>(column.getId()));
            });
        }

    }

}
