package collection.ui;

import collection.tcg.Card;
import collection.tcg.CardPersistence;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

public class CollectionMenuController implements Initializable {

    private final CardPersistence cardPersistence = new CardPersistence();

    @FXML
    private TabPane collectionTabPane;

    @FXML
    private TableView<Card> tcgTable;

    @FXML
    private Button createNewTcgCard;

    @FXML
    public void initialize(final URL location, final ResourceBundle resourceBundle) {
        TableViewUtil.configTableCellValueFactory(tcgTable);
        collectionTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> loadDataAccordingToSelectedTab());
        loadDataAccordingToSelectedTab();
        configContextMenu();
        configTcgEvents();
    }

    private void configContextMenu() {
        final ContextMenu contextMenu = new ContextMenu();
        final MenuItem menuItem = new MenuItem("Delete Record");
        menuItem.setOnAction(event -> {
            deleteAccordingToSelectedTab();
            loadDataAccordingToSelectedTab();
        });
        contextMenu.getItems().add(menuItem);
        collectionTabPane.setContextMenu(contextMenu);
    }

    private void configTcgEvents() {

        createNewTcgCard.setOnAction(event -> {
            cardPersistence.addCard(new Card(
                    null,
                    "Flesh and Blood",
                    "High Voltage",
                    "Rare",
                    "History Pack",
                    "7",
                    "Very cool"
            ));
            loadDataAccordingToSelectedTab();
        });

    }

    private void loadDataAccordingToSelectedTab() {
        final Tab tab = collectionTabPane.getSelectionModel().getSelectedItem();
        if (tab.getId().equalsIgnoreCase("tabTcg")) {
            tcgTable.setItems(FXCollections.observableArrayList(cardPersistence.loadCards()));
        }
    }

    private void deleteAccordingToSelectedTab() {
        final Tab tab = collectionTabPane.getSelectionModel().getSelectedItem();
        if (tab.getId().equalsIgnoreCase("tabTcg")) {
            final Long cardId = tcgTable.getSelectionModel().getSelectedItem().getId();
            cardPersistence.removeCard(cardId);
        }
    }

}
