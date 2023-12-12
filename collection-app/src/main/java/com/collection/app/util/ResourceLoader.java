package com.collection.app.util;

import com.collection.app.CollectionApp;
import java.net.URL;

/**
 * Uitlity class to load java resources. Used for loading "fxml" files.
 */
public final class ResourceLoader {

  private ResourceLoader() {
    // noop
  }

  /**
   * Load a resource based on {@link CollectionApp} class.
   * @param resource {@link String} resource name.
   * @return {@link URL} resource URL.
   */
  public static URL load(final String resource) {
    return CollectionApp.class.getResource(resource);
  }

}
