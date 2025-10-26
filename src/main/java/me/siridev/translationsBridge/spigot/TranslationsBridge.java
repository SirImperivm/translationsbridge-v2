package me.siridev.translationsBridge.spigot;

import me.siridev.translationsBridge.spigot.assets.handlers.ConfigHandler;
import me.siridev.translationsBridge.spigot.assets.utils.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("all")
public final class TranslationsBridge extends JavaPlugin {

    private TranslationsBridge plugin;
    private static TranslationsBridge Spigot;

    private ConfigHandler configHandler;

    @Override
    public void onLoad() {
        plugin = this;
        Spigot = this;
    }

    @Override
    public void onEnable() {
        configHandler = new ConfigHandler(plugin);

        Logger.Success("Plugin correctly enabled.");
        Logger.Stamp();
    }

    @Override
    public void onDisable() {

        Logger.Success("Plugin correctly disabled.");
        Logger.Stamp();
    }

    public void disable() {
        getPluginManager().disablePlugin(this);
    }

    public TranslationsBridge getPlugin() {
        return plugin;
    }

    public static TranslationsBridge getSpigot() {
        return Spigot;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
