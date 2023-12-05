package collection.usersettings;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadUserSettings {

    private static final String CONFIG_FOLDER = System.getProperty("user.home")
            .concat(File.separator)
            .concat(".collection")
            .concat(File.separator)
            .concat("user-settings.json");

    public UserSettings loadUserSettings() {
        final Path path = Paths.get(CONFIG_FOLDER);

        try {

            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return new UserSettings();
            }

            final String fileContent = Files.readString(path);
            return new Gson().fromJson(fileContent, UserSettings.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
