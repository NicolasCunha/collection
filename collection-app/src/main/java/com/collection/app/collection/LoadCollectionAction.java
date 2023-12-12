package com.collection.app.collection;

import com.collection.app.audit.AuditService;
import com.collection.app.usersettings.UserSettings;
import com.collection.app.util.StageHolder;

/**
 * Reusable action to load a collection and redirect the user to the main menu.
 * Used when user loads through a lookup and using the recent collections table view.
 */
public class LoadCollectionAction {

  /**
   * Loads a collection to memory and forwards the user to the main menu screen.
   *
   * <p>The selected collection is stored in a property inside JavaFX's primary stage,
   * with the name "current_collection".
   *
   * <p>The stage and its properties can be accessed through the
   * static method {@link StageHolder#getStage()}.
   *
   * <p>Each subsequent operation that requires a reference to the selected collection
   * will look up the Stage properties to fetch its reference.
   *
   * @param collection {@link Collection} to be loaded.
   */
  public void loadCollection(final Collection collection) {
    if (StageHolder.getCurrentScene() != null) {
      StageHolder.close();
    }
    AuditService.audit("Loading selected collection: %s", collection.getName());
    final UserSettings userSettings = UserSettings.load();
    userSettings.addRecentCollection(collection.getUuid());
    userSettings.save();
    StageHolder.getStage().getProperties().put("current_collection", collection);
    StageHolder.closeAndOpen("collection-menu.fxml", "Collection - Menu");
  }

}
