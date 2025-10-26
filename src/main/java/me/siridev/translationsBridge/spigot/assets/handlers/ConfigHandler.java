package me.siridev.translationsBridge.spigot.assets.handlers;

import me.siridev.translationsBridge.spigot.TranslationsBridge;
import me.siridev.translationsBridge.spigot.assets.utils.Logger;
import me.siridev.translationsBridge.spigot.objects.entities.files.PluginFile;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class ConfigHandler {

    private TranslationsBridge plugin;

    private File folder;
    private File localesFolder;

    public static Map<String, PluginFile> PluginFiles = new HashMap<>();

    public ConfigHandler(TranslationsBridge plugin) {
        this.plugin = plugin;

        folder = plugin.getDataFolder();
        localesFolder = new File(folder, "locales");

        if (!folder.exists()) folder.mkdirs();
        if (!localesFolder.exists()) localesFolder.mkdirs();

        PluginFiles.clear();
        PluginFiles.put("settings", new PluginFile("settings", folder, true, true));
        PluginFiles.put("messages", new PluginFile("messages", folder, true, true));

        createAll();
        loadAll(false);
        validateAll();
    }

    public static void save(String fileName) {
        if (!PluginFiles.containsKey(fileName)) {
            Logger.Fail("Tried to save non-existent file!");
            return;
        }

        try {
            PluginFiles.get(fileName).save();
        } catch (IOException e) {
            Logger.Fail("Failed to save " + fileName + " file!");
            e.printStackTrace();
        }
    }

    public static void load(String fileName) {
        if (!PluginFiles.containsKey(fileName)) {
            Logger.Fail("Tried to load non-existent file!");
            return;
        }

        try {
            PluginFiles.get(fileName).load();
        } catch (IOException | InvalidConfigurationException e) {
            Logger.Fail("Failed to load " + fileName + " file!");
            e.printStackTrace();
        }
    }

    private void createAll() {
        for (Map.Entry<String, PluginFile> entry : PluginFiles.entrySet()) {
            String name = entry.getKey();
            PluginFile file = entry.getValue();

            try {
                file.create();
            } catch (IOException e) {
                Logger.Fail("Failed to create " + name + " file! Breaking.");
                e.printStackTrace();
                plugin.disable();
                return;
            }
        }
    }

    public void saveAll() {
        for (Map.Entry<String, PluginFile> entry : PluginFiles.entrySet()) {
            String name = entry.getKey();
            PluginFile file = entry.getValue();

            try {
                file.save();
            } catch (Exception e) {
                Logger.Fail("Failed to save " + name + " file! Skipping.");
                e.printStackTrace();
            }
        }
    }

    public void loadAll(boolean reloading) {
        for (Map.Entry<String, PluginFile> entry : PluginFiles.entrySet()) {
            String name = entry.getKey();
            PluginFile file = entry.getValue();

            if (reloading && !file.isReloadable()) continue;
            try {
                file.load();
            } catch (IOException | InvalidConfigurationException e) {
                Logger.Fail("Failed to load " + name + " file! Skipping.");
                e.printStackTrace();
            }
        }
    }

    public void validateAll() {
        for (Map.Entry<String, PluginFile> entry : PluginFiles.entrySet()) {
            String name = entry.getKey();
            PluginFile file = entry.getValue();

            try {
                file.validate();
            } catch (IOException e) {
                Logger.Fail("Failed to update " + name + " file version! Skipping.");
                e.printStackTrace();
            }
        }
    }

    public File getFolder() {
        return folder;
    }

    public File getLocalesFolder() {
        return localesFolder;
    }
}
