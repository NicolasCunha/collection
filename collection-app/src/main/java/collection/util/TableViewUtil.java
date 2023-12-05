package collection.util;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewUtil {

    public static void configTableCellValueFactory(final TableView<?>... tables) {

        for(final TableView<?> table : tables) {
            table.getColumns().forEach(column -> {
                // Ids and class properties are mapped to be equal.
                column.setCellValueFactory(new PropertyValueFactory<>(column.getId()));
            });
        }

    }

}
