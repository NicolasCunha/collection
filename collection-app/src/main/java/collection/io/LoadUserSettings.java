package collection.io;

import collection.data.UserSettings;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadUserSettings {

    public UserSettings loadUserSettings() {
        final Path path = Paths.get(CollectionPath.CONFIG_FOLDER);

        if (!Files.exists(path)) {
            return null;
        }

        try {
            final String fileContent = Files.readString(path);
            return new Gson().fromJson(fileContent, UserSettings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
