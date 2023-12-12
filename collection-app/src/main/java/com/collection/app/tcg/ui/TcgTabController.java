package com.collection.app.tcg.ui;

import com.collection.app.CollectionDataHolder;
import com.collection.app.tcg.Card;
import com.collection.app.tcg.CardService;
import com.collection.app.util.TableViewUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class TcgTabController implements Initializable {

    private final CardService cardService = new CardService();

    @FXML
    private TableView<Card> tcgTable;

    @FXML
    private Button createNewTcgCard;

    @FXML
    public void initialize(final URL location, final ResourceBundle resourceBundle) {
        TableViewUtil.configTableCellValueFactory(tcgTable);
        configEvents();
        loadData();
    }

    private void configEvents() {

        createNewTcgCard.setOnAction(event -> {
            final Card card = Card.create(
                    CollectionDataHolder.CURRENT_COLLECTION,
                    null,
                    "Flesh and Blood",
                    "High Voltage",
                    "Rare",
                    "History Pack",
                    "7",
                    "Very cool"
            );
            cardService.addCard(card);
            loadData();
        });

    }

    public void loadData() {
        tcgTable.setItems(FXCollections.observableArrayList(cardService.loadCards(
                CollectionDataHolder.CURRENT_COLLECTION
        )));
    }

}
