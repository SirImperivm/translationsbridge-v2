package me.siridev.translationsBridge.platform.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.siridev.translationsBridge.core.TranslationsBridgeAPI;
import me.siridev.translationsBridge.core.api.TranslationAPI;
import me.siridev.translationsBridge.platform.velocity.utils.Formatter;

import java.io.File;
import java.nio.file.Path;

@SuppressWarnings("all")
public class TranslationsBridge {

    private final ProxyServer server;
    private final Path dataDirectory;

    void Log(String message) {
        server.getConsoleCommandSource().sendMessage(
                Formatter.fromAmpersand(message)
        );
    }

    @Inject
    public TranslationsBridge(ProxyServer server, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.dataDirectory = dataDirectory;
    }

    @Inject
    public void onProxyInitialization() {
        Log("Plugin enabled.");

        File dataFolder = dataDirectory.toFile();
        if (!dataFolder.exists()) dataFolder.mkdirs();

        TranslationsBridgeAPI.SetHandler(new TranslationAPI(dataFolder));

        Log("API initialized.");
    }
}
