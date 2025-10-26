package me.siridev.translationsBridge.bungee;

import me.siridev.translationsBridge.bungee.assets.handlers.ConfigHandler;
import me.siridev.translationsBridge.bungee.assets.utils.Logger;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("all")
public class TranslationsBridge extends Plugin {

    private TranslationsBridge plugin;
    private static TranslationsBridge Bungee;

    private ConfigHandler configHandler;

    @Override
    public void onLoad() {
        plugin = this;
        Bungee = this;
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
        getProxy().getPluginManager().getPlugin("TranslationsBridge").onDisable();
    }

    public TranslationsBridge getPlugin() {
        return plugin;
    }

    public static TranslationsBridge getBungee() {
        return Bungee;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
