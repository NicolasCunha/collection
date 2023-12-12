package com.collection.app.util;

import com.collection.app.CollectionApp;
import java.net.URL;

public final class ResourceLoader {

    private ResourceLoader() {
        // noop
    }

    public static URL load(final String resource) {
        return CollectionApp.class.getResource(resource);
    }

}
