package me.siridev.translationsBridge.bungee.assets.utils;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

import static me.siridev.translationsBridge.bungee.TranslationsBridge.getBungee;

@SuppressWarnings("all")
public class Logger {

    public static void Success(String message) {
        getBungee().getProxy().getConsole().sendMessage(Formatter.Translate("&a[TranslationsBridge] " + message));
    }

    public static void Warning(String message) {
        getBungee().getProxy().getConsole().sendMessage(Formatter.Translate("&e[TranslationsBridge] " + message));
    }

    public static void Fail(String message) {
        getBungee().getProxy().getConsole().sendMessage(Formatter.Translate("&c[TranslationsBridge] " + message));
    }

    public static void Stamp() {
        String stampMessage;
        try {
            stampMessage = FigletFont.convertOneLine("TranslationsBridge");
        } catch (IOException e) {return;}
        getBungee().getProxy().getConsole().sendMessage("\n" + stampMessage + "\n" + Formatter.Translate("&aPlugin developed by SirImperivm_!"));
    }
}
