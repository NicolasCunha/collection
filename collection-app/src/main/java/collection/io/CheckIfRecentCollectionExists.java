package collection.io;

import collection.data.UserSettings;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckIfRecentCollectionExists {

    public boolean checkIfExists() {

        final Path path = Paths.get(CollectionPath.CONFIG_FOLDER);

        if (!Files.exists(path)) {
            return false;
        }

        try {
            final String fileContent = Files.readString(path);
            final UserSettings userSettings = new Gson().fromJson(fileContent, UserSettings.class);
            return userSettings.getRecentFile() != null && !userSettings.getRecentFile().isEmpty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
