package collection.audit;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class AuditToFileThread extends Thread {

    private final Queue<String> queue = new LinkedList<>();

    @Override
    public void run() {
        try {
            while (true) {
                if (queue.isEmpty()) {
                    sleep(5000);
                }
                while (!queue.isEmpty()) {
                    appendToFile(queue.poll());
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void append(final String message) {
        queue.add(message);
    }

    private void appendToFile(final String message) {

        final String auditFileDir = System.getProperty("user.home")
                .concat(File.separator)
                .concat(".collection")
                .concat(File.separator)
                .concat("audit_log.txt");

        final Path auditFilePath = Paths.get(auditFileDir);

        try {

            if (!Files.exists(auditFilePath)) {
                Files.createDirectories(auditFilePath.getParent());
                Files.createFile(auditFilePath);
            }

            final String auditMessage = String.format("%s - %s \n",
                    Instant.now().toString(),
                    message);

            Files.write(auditFilePath, auditMessage.getBytes(), StandardOpenOption.APPEND);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }

    }

}
