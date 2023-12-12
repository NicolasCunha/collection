package com.collection.app;

import com.collection.app.audit.AuditService;
import com.collection.app.util.HibernateUtil;
import com.collection.app.util.StageHolder;
import javafx.application.Application;
import javafx.stage.Stage;

public class CollectionApp extends Application {

    @Override
    public void start(Stage stage) {
        showMainScreen(stage);
    }

    private void showMainScreen(Stage stage) {
        StageHolder.defineScene(stage);
        AuditService.audit("Starting application.");
        StageHolder.open("collection-startup.fxml", "Collection - Main Menu");
    }

    public static void main(String[] args) {
        // TODO: code clean up and add checkstyle
        loadDatabaseSession();
        launch();
    }

    private static void loadDatabaseSession() {
        AuditService.audit("Starting loading database session.");
        HibernateUtil.getSessionFactory();
        AuditService.audit("Finished loading database session.");
    }

}