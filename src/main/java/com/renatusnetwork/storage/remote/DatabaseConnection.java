package com.renatusnetwork.storage.remote;

import com.renatusnetwork.Projects;
import com.renatusnetwork.managers.DatabaseManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        String host = settings.getString(dbPath + ".host");
        String database = settings.getString(dbPath + ".database");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true" + "&allowMultiQueries=true" + "&useSSL=false";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Projects.getPluginLogger().info("MySQL connected successfully");
    }

    public void close() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection get() {
        return connection;
    }

    public DatabaseMetaData getMeta() {
        DatabaseMetaData meta = null;

        try {
            meta = get().getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return meta;
    }
}
