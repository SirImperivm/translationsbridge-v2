package me.siridev.translationsBridge.bungee.assets.handlers;

import me.siridev.translationsBridge.bungee.TranslationsBridge;
import me.siridev.translationsBridge.bungee.assets.utils.Logger;
import me.siridev.translationsBridge.bungee.objects.entities.files.PluginFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class ConfigHandler {

    private TranslationsBridge plugin;

    private File folder, localesFolder;
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
            } catch (IOException e) {
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
            } catch (IOException e) {
                Logger.Fail("Failed to load " + name + " file! Breaking.");
                e.printStackTrace();
                if (!reloading) plugin.disable();
                return;
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
                Logger.Fail("Failed to validate " + name + " file! Skipping.");
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
