package com.collection.app.collection;

import com.collection.app.audit.AuditService;
import com.collection.app.usersettings.UserSettings;
import com.collection.app.util.StageHolder;

/**
 * Reusable action to load a collection.
 * Used when user loads through a lookup and using the recent collections table view.
 */
public class LoadCollectionAction {

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
