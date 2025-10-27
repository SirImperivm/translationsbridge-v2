package me.siridev.translationsBridge.platform.spigot;

import me.siridev.translationsBridge.core.TranslationsBridgeAPI;
import me.siridev.translationsBridge.core.api.TranslationAPI;
import me.siridev.translationsBridge.platform.spigot.utils.Formatter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static org.bukkit.Bukkit.getConsoleSender;

@SuppressWarnings("all")
public class TranslationsBridge extends JavaPlugin {

    static void Log(String message) {
        getConsoleSender().sendMessage(Formatter.Format("&e[TranslationsBridge] " + message));
    }

    @Override
    public void onEnable() {
        Log("Plugin enabled.");

        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdirs();

        TranslationsBridgeAPI.SetHandler(new TranslationAPI(dataFolder));

        Log("API initialized.");
    }

    @Override
    public void onDisable() {
        Log("Plugin disabled.");
    }
}
