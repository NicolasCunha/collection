package collection.io;

import java.io.File;

public abstract class CollectionPath {

    public static final String CONFIG_FOLDER = System.getProperty("user.home")
            .concat(File.separator)
            .concat(".collection-settings");

    public static final String DEFAULT_COLLECTION_PATH = System.getProperty("user.home")
            .concat(File.separator)
            .concat("collection-data")
            .concat(File.separator);

}
