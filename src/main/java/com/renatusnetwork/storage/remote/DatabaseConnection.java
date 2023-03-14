package com.renatusnetwork.storage.remote;

import com.renatusnetwork.Projects;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;

public class DatabaseConnection {

    private Connection connection;

    public DatabaseConnection() {
        open();
    }

    private void open() {
        FileConfiguration settings = Projects.getFileManager().get("settings");
        String dbPath = "database";

        String username = settings.getString(dbPath + ".username");
        String password = settings.getString(dbPath + ".password");
        String port = settings.getString(dbPath + ".port");
    }
}
