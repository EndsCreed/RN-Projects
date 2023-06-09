package com.renatusnetwork.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {

    private Map<String, File> files = new HashMap<>();
    private Map<String, FileConfiguration> configs = new HashMap<>();

    public FileManager (Plugin plugin) {
        init("settings", plugin);
        init("menus", plugin);
        init("divisions", plugin);
    }

    private void init(String fileName, Plugin plugin) {
        File file = new File(plugin.getDataFolder(), fileName + ".yml");
        FileConfiguration fileConfig = new YamlConfiguration();

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                copy(plugin.getResource("config/" + fileName + ".yml"), file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        files.put(fileName, file);
        configs.put(fileName, fileConfig);

        load(fileName);
    }

    public void load(String fileName) {
        try {
            configs.get(fileName).load(files.get(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName) {
        try {
            configs.get(fileName).save(files.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration get(String fileName) {
        FileConfiguration fileConfig = configs.get(fileName);
        if (fileConfig != null)
            return fileConfig;
        return null;
    }
}
