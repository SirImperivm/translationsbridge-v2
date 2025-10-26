package me.siridev.translationsBridge.spigot.assets.utils;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

import static me.siridev.translationsBridge.spigot.TranslationsBridge.getSpigot;

@SuppressWarnings("all")
public class Logger {

    public static void Success(String message) {
        getSpigot().getServer().getConsoleSender().sendMessage(Formatter.Translate("&a[TranslationsBridge] " + message));
    }

    public static void Warning(String message) {
        getSpigot().getServer().getConsoleSender().sendMessage(Formatter.Translate("&e[TranslationsBridge] " + message));
    }

    public static void Fail(String message) {
        getSpigot().getServer().getConsoleSender().sendMessage(Formatter.Translate("&c[TranslationsBridge] " + message));
    }

    public static void Stamp() {
        String stampMessage;
        try {
            stampMessage = FigletFont.convertOneLine("TranslationsBridge");
        } catch (IOException e) {return;}
        getSpigot().getServer().getConsoleSender().sendMessage("\n" + stampMessage + "\n" + Formatter.Translate("&aPlugin developed by SirImperivm_!"));
    }
}
