package me.siridev.translationsBridge.spigot.objects.entities.files;

import me.siridev.translationsBridge.spigot.TranslationsBridge;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import static me.siridev.translationsBridge.spigot.TranslationsBridge.getSpigot;

@SuppressWarnings("all")
public class PluginFile {

    private String name;
    private File folder;

    private File file;
    private FileConfiguration config;

    private boolean validable, reloadable;

    public PluginFile(String name, File folder, boolean validable, boolean reloadable) {
        this.name = name;
        this.folder = folder;
        this.validable = validable;
        this.reloadable = reloadable;

        file = new File(folder, name + ".yml");
        config = new YamlConfiguration();
    }

    public void create() throws IOException {
        if (file.exists()) return;

        Files.copy(getSpigot().getResource("configs/spigot/" + name + ".yml"), file.toPath(), new CopyOption[0]);
    }

    public void save() throws IOException {
        config.save(file);
    }

    public void load() throws IOException, InvalidConfigurationException {
        config.load(file);
    }

    public void validate() throws IOException {
        if (!validable) return;
        TranslationsBridge plugin = getSpigot();

        InputStream originalFileStream = plugin.getResource("configs/spigot/" + name + ".yml");
        if (originalFileStream == null) return;

        FileConfiguration originalConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(originalFileStream));
        int changes = 0;

        Set<String> keys = config.getKeys(true);
        Set<String> originalKeys = originalConfig.getKeys(true);

        for (String key : new HashSet<>(keys)) {
            if (!originalKeys.contains(key)) {
                config.set(key, null);
                changes++;
            }
        }

        for (String key : originalKeys) {
            if (!keys.contains(key)) {
                Object originalValue = originalConfig.get(key);
                config.set(key, originalValue);
                changes++;
            }
        }

        if (changes > 0) save();
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public boolean isReloadable() {
        return reloadable;
    }
}
