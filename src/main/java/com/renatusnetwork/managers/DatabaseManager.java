package com.renatusnetwork.managers;

import com.renatusnetwork.Projects;
import com.renatusnetwork.storage.remote.DatabaseConnection;
import com.renatusnetwork.storage.remote.TablesDB;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private DatabaseConnection connection;
    private boolean running = false;
    private List<String> cache = new ArrayList<>();
    public DatabaseManager(Plugin plugin) {
        connection = new DatabaseConnection();
        TablesDB.configure(this);
        startScheduler(plugin);
    }

    public void close() {
        runCaches();
        connection.close();
    }

    private void startScheduler(Plugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> runCaches(), 0L, 2L);

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PreparedStatement statement = connection.get().prepareStatement(
                            "SELECT * FROM players WHERE UUID='s'");
                    statement.execute();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 20 * 60 * 10, 20 * 60 * 10);
    }

    public DatabaseConnection get() {
        return connection;
    }

    private void runCaches() {
        if (running) // makes sure it isn't already running
            return;

        running = true; // ensures this method run the only one that can run

        try {

            if (cache.size() > 0)
                runCache();

        } catch (Exception exception) {
            Projects.getPluginLogger().severe("ERROR: Occurred within DatabaseManager.runCaches()");
            Projects.getPluginLogger().severe("ERROR:  printing StackTrace");
            exception.printStackTrace();
        }

        running = false; // allows the method to be ran again
    }

    private void runCache() {
        try {
            String finalQuery = "";

            List<String> tempCache = new ArrayList<>(cache);

            for (String sql : tempCache)
                finalQuery = finalQuery + sql + "; ";

            run(finalQuery);
            cache.removeAll(tempCache); // removes queries that have been ran
        } catch (Exception exception) {
            Projects.getPluginLogger().severe("ERROR: Occurred within DatabaseManager.runCache()");
            Projects.getPluginLogger().severe("ERROR:  printing StackTrace");
            exception.printStackTrace();
        }
    }

    public void add(String sql) {
        cache.add(sql);
    }

    public void run(String sql) {
        try {
            PreparedStatement statement = connection.get().prepareStatement(sql);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            Projects.getPluginLogger().severe("ERROR: SQL Failed to run query: " + sql);
            e.printStackTrace();
        }
    }

    public void asyncRun(String sql) {
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement statement = connection.get().prepareStatement(sql);
                    statement.execute();
                    statement.close();
                } catch (SQLException e) {
                    Projects.getPluginLogger().severe("ERROR: SQL Failed to asyncRun query: " + sql);
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Projects.getPlugin());
    }
}
