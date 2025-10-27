package me.siridev.translationsBridge.platform.bungee;

import me.siridev.translationsBridge.core.TranslationsBridgeAPI;
import me.siridev.translationsBridge.core.api.TranslationAPI;
import me.siridev.translationsBridge.platform.bungee.utils.Formatter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

@SuppressWarnings("all")
public class TranslationsBridge extends Plugin {

    static void Log(String message) {
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(Formatter.Format("&e[TranslationsBridge] " + message)));
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
