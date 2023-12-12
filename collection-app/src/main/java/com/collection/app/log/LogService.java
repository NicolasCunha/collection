package com.collection.app.log;

import java.time.Instant;

public class LogService {

    public static void log(final String logMessage) {
        final String logFormat = String.format("%s - %s", Instant.now().toString(), logMessage);
        System.out.println(logFormat);
    }

}
