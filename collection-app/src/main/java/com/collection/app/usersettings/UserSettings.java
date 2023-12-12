package com.collection.app.usersettings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class UserSettings {

    private static final String USER_SETTINGS_PATH = System.getProperty("user.home")
            .concat(File.separator)
            .concat(".collection")
            .concat(File.separator)
            .concat("user-settings.json");

    private List<UUID> recentCollectionIds = new LinkedList<>();

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
            throw new RuntimeException();
        }

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

    public void addRecentCollection(final UUID collectionId) {
        if (!getRecentCollections().contains(collectionId)) {
            getRecentCollections().add(collectionId);
        }
    }

    public List<UUID> getRecentCollections() {
        return recentCollectionIds;
    }

    public void setRecentCollections(List<UUID> recentCollections) {
        this.recentCollectionIds = recentCollections;
    }
}
