package com.renatusnetwork;

import com.renatusnetwork.commands.ProjectsCMD;
import com.renatusnetwork.managers.DatabaseManager;
import com.renatusnetwork.managers.FileManager;
import com.renatusnetwork.managers.MenuManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Projects extends JavaPlugin {
    private static Projects plugin;
    private static Logger logger;
    private static DatabaseManager databaseManager;
    private static FileManager fileManager;
    private static MenuManager menuManager;

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    private void registerEvents() {

    }

    private void registerCommands() {
        getCommand("projects").setExecutor(new ProjectsCMD());
    }

    // Getters
    public static FileManager getFileManager() { return fileManager; }
    public DatabaseManager getDatabaseManager() { return databaseManager; }
    public MenuManager getMenuManager() { return menuManager; }
}