package collection.core.ui;

import collection.CollectionDataHolder;
import collection.core.Collection;
import collection.core.CollectionService;
import collection.util.DialogUtil;
import javafx.scene.control.TextInputDialog;

public class CheckCreateNewCollection {

    private final CollectionService collectionService = new CollectionService();

    public void checkAnyCollectionExists() {
        final CollectionService collectionService = new CollectionService();


        if (!collectionService.existsAnyCollection()) {
            if (DialogUtil.confirm("No collection has been found. Do you want to create a new collection?")) {
                final String collectionName = inputCollectionName();
                final Collection collection = Collection.create(collectionName);
                this.collectionService.save(collection);
                CollectionDataHolder.CURRENT_COLLECTION = collection;
            }
        } else {
            // TODO input with user which collection he wants to load
        }

    }

    private String inputCollectionName() {
        final TextInputDialog textInputDialog = new TextInputDialog("New Collection");
        textInputDialog.setHeaderText(null);
        textInputDialog.setTitle("Collection");
        textInputDialog.setContentText("Inform the new collection name:");
        textInputDialog.showAndWait();
        return textInputDialog.getDefaultValue();
    }

}
