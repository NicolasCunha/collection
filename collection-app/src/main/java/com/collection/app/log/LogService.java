package com.collection.app.log;

import java.time.Instant;

/**
 * Service responsible for logging.
 */
public final class LogService {

  private LogService() {
    // noop
  }

  public static void log(final String logMessage) {
    final String logFormat = String.format("%s - %s", Instant.now().toString(), logMessage);
    System.out.println(logFormat);
  }

}
