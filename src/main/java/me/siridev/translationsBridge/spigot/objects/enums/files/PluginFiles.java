package me.siridev.translationsBridge.spigot.objects.enums.files;

import me.siridev.translationsBridge.spigot.assets.utils.Formatter;
import me.siridev.translationsBridge.spigot.objects.entities.files.PluginFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static me.siridev.translationsBridge.spigot.TranslationsBridge.getSpigot;

@SuppressWarnings("all")
public enum PluginFiles {

    SETTINGS(getSpigot().getConfigHandler().PluginFiles.get("settings")),
    MESSAGES(getSpigot().getConfigHandler().PluginFiles.get("messages")),
    ;

    private PluginFile pluginFile;

    PluginFiles(PluginFile pluginFile) {
        this.pluginFile = pluginFile;
    }

    public void save() throws IOException {
        pluginFile.getConfig().save(pluginFile.getFile());
    }

    public FileConfiguration getConfig() {
        return pluginFile.getConfig();
    }

    public String getString(String path) {
        return getString(path, null);
    }
    public String getString(String path, String def) {
        return getConfig().getString(path, def);
    }

    public String wrapString(String path) {
        return getConfig().get(path) instanceof String ? getConfig().getString(path) : String.valueOf(getConfig().get(path));
    }
    public String wrapString(String path, Object def) {
        return getConfig().get(path) == null ? (String) def : wrapString(path);
    }

    public String getTranslatedString(String path, Map<String, String> placeholders) {
        return getTranslatedString(path, null, placeholders);
    }
    public String getTranslatedString(String path, String def, Map<String, String> placeholders) {
        return Formatter.Translate(getString(path, def), placeholders);
    }

    public int getInt(String path) {
        return getConfig().getInt(path);
    }
    public int getInt(String path, int def) {
        return getConfig().getInt(path, def);
    }

    public long getLong(String path) {
        return getConfig().getLong(path);
    }
    public long getLong(String path, long def) {
        return getConfig().getLong(path, def);
    }

    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }
    public double getDouble(String path, double def) {
        return getConfig().getDouble(path, def);
    }

    public float getFloat(String path) {
        return (float) getDouble(path);
    }
    public float getFloat(String path, float def) {
        return (float) getDouble(path, def);
    }

    public boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }
    public boolean getBoolean(String path, boolean def) {
        return getConfig().getBoolean(path, def);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return getConfig().getConfigurationSection(path);
    }

    public List<?> getList(String path) {
        return getConfig().getList(path);
    }
    public List<?> getList(String path, List<?> def) {
        return getConfig().getList(path, def);
    }

    public List<String> getStringList(String path) {
        return getConfig().getStringList(path);
    }
    public List<String> getStringList(String path, List<String> def) {
        return getStringList(path) != null ? getStringList(path) : def;
    }

    public List<String> getTranslatedList(String path, Map<String, String> placeholders) {
        return Formatter.Translate(getStringList(path), placeholders);
    }
    public List<String> getTranslatedList(String path, List<String> def, Map<String, String> placeholders) {
        return Formatter.Translate(getStringList(path, def), placeholders);
    }

    public List<Integer> getIntegerList(String path) {
        return getConfig().getIntegerList(path);
    }
    public List<Integer> getIntegerList(String path, List<Integer> def) {
        return getIntegerList(path) != null ? getIntegerList(path) : def;
    }

    public List<Double> getDoubleList(String path) {
        return getConfig().getDoubleList(path);
    }
    public List<Double> getDoubleList(String path, List<Double> def) {
        return getDoubleList(path) != null ? getDoubleList(path) : def;
    }

    public List<Float> getFloatList(String path) {
        return getConfig().getFloatList(path);
    }
    public List<Float> getFloatList(String path, List<Float> def) {
        return getFloatList(path) != null ? getFloatList(path) : def;
    }

    public List<Long> getLongList(String path) {
        return getConfig().getLongList(path);
    }
    public List<Long> getLongList(String path, List<Long> def) {
        return getLongList(path) != null ? getLongList(path) : def;
    }

    public List<Boolean> getBooleanList(String path) {
        return getConfig().getBooleanList(path);
    }
    public List<Boolean> getBooleanList(String path, List<Boolean> def) {
        return getBooleanList(path) != null ? getBooleanList(path) : def;
    }

    public List<Byte> getByteList(String path) {
        return getConfig().getByteList(path);
    }
    public List<Byte> getByteList(String path, List<Byte> def) {
        return getByteList(path) != null ? getByteList(path) : def;
    }

    public List<Character> getCharacterList(String path) {
        return getConfig().getCharacterList(path);
    }
    public List<Character> getCharacterList(String path, List<Character> def) {
        return getCharacterList(path) != null ? getCharacterList(path) : def;
    }

    public List<Short> getShortList(String path) {
        return getConfig().getShortList(path);
    }
    public List<Short> getShortList(String path, List<Short> def) {
        return getShortList(path) != null ? getShortList(path) : def;
    }

    public List<Map<?, ?>> getMapList(String path) {
        return getConfig().getMapList(path);
    }
    public List<Map<?, ?>> getMapList(String path, List<Map<?, ?>> def) {
        return getMapList(path) != null ? getMapList(path) : def;
    }
}
