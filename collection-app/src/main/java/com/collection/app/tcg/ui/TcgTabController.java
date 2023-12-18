package com.collection.app.tcg.ui;

import com.collection.app.collection.Collection;
import com.collection.app.tcg.Card;
import com.collection.app.tcg.CardService;
import com.collection.app.util.StageHolder;
import com.collection.app.util.TableViewUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * JavaFX Controller responsible for the "TCG" tab.
 */
public class TcgTabController implements Initializable {

  private final CardService cardService = new CardService();

  @FXML
  private TableView<Card> tcgTable;

  @FXML
  private Button createNewTcgCard;

  /**
   * Initialize the JavaFX controller instance.
   *
   * @param location       The location used to resolve relative paths for the root object,
   *                       or {@code null} if the location is not known.
   * @param resourceBundle The resources used to localize the root object,
   *                       or {@code null} if the root object was not localized.
   */
  @FXML
  public void initialize(final URL location, final ResourceBundle resourceBundle) {
    Platform.runLater(() -> {
      TableViewUtil.configTableCellValueFactory(tcgTable);
      configureEvents();
      loadData();
    });
  }

  private void configureEvents() {
    createNewTcgCard.setOnAction(event -> {
      StageHolder.closeAndOpen("collection-create-new-card.fxml", "Collection - Create New Card");
    });
  }

  private void loadData() {
    final Collection collection =
        (Collection) (this.createNewTcgCard).getScene().getWindow().getUserData();
    tcgTable.setItems(FXCollections.observableArrayList(cardService.loadCards(collection)));
  }

}
