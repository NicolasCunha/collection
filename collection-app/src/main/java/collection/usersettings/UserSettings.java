package collection.usersettings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class UserSettings {

    private static final String USER_SETTINGS_PATH = System.getProperty("user.home")
            .concat(File.separator)
            .concat(".collection")
            .concat(File.separator)
            .concat("user-settings.json");

    private List<String> recentCollections = new LinkedList<>();

    public static UserSettings load() {

        final Path path = Paths.get(USER_SETTINGS_PATH);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                final UserSettings userSettings = new UserSettings();
                userSettings.save();
                return userSettings;
            }

            final String json = Files.readString(path);
            return new Gson().fromJson(json, UserSettings.class);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public void save() {
        final Path path = Paths.get(USER_SETTINGS_PATH);
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final String json = gson.toJson(this);
            Files.write(path, json.getBytes());
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getRecentCollections() {
        return recentCollections;
    }

    public void setRecentCollections(List<String> recentCollections) {
        this.recentCollections = recentCollections;
    }
}
