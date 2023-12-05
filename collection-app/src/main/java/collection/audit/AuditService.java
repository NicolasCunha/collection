package collection.audit;

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
        AuditService.auditToFileThread.append(message);
    }

}
