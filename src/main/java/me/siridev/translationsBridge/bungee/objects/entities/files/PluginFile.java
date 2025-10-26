package me.siridev.translationsBridge.bungee.objects.entities.files;

import me.siridev.translationsBridge.bungee.TranslationsBridge;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import static me.siridev.translationsBridge.bungee.TranslationsBridge.getBungee;

@SuppressWarnings("all")
public class PluginFile {

    private String name;
    private File folder;

    private File file;
    private Configuration config;

    private boolean validable, reloadable;

    public PluginFile(String name, File folder, boolean validable, boolean reloadable) {
        this.name = name;
        this.folder = folder;
        this.validable = validable;
        this.reloadable = reloadable;

        file = new File(folder, name + ".yml");
    }

    public void create() throws IOException {
        if (file.exists()) return;

        Files.copy(getBungee().getResourceAsStream("configs/bungee/" + name + ".yml"), file.toPath(), new CopyOption[0]);
    }

    public void save() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
    }

    public void load() throws IOException {
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }

    public void validate() throws IOException {
        if (!validable) return;
        TranslationsBridge plugin = getBungee();

        InputStream originalFileStram = plugin.getResourceAsStream("configs/bungee/" + name + ".yml");
        if (originalFileStram == null) return;

        Configuration originalConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(originalFileStram);
        int changes = 0;

        Set<String> keys = collectKeys(config);
        Set<String> originalKeys = collectKeys(originalConfig);

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

    private Set<String> collectKeys(Configuration config) {
        Set<String> result = new HashSet<>();
        collectKeysRecursive("", config, result);
        return result;
    }

    private void collectKeysRecursive(String prefix, Configuration config, Set<String> out) {
        for (String key : config.getKeys()) {
            String full = prefix.isEmpty() ? key : prefix + "." + key;
            out.add(full);

            Object value = config.get(key);
            if (value instanceof Configuration) {
                collectKeysRecursive(full, (Configuration) value, out);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Configuration getConfig() {
        return config;
    }

    public boolean isReloadable() {
        return reloadable;
    }
}
