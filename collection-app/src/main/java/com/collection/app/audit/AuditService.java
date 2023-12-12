package com.collection.app.audit;

import com.collection.app.log.LogService;

public final class AuditService {

    private static final AuditToFileThread auditToFileThread = new AuditToFileThread();

    static {
        final Thread thread = new Thread(auditToFileThread);
        thread.start();
    }

    private AuditService() {
        // noop
    }

    public static void audit(final String message) {
        LogService.log(message);
        AuditService.auditToFileThread.append(message);
    }

    public static void audit(final String message, final Object... args) {
        LogService.log(String.format(message, args));
        AuditService.auditToFileThread.append(String.format(message, args));
    }

}
