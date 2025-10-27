package me.siridev.translationsBridge.platform.spigot.utils;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("all")
public class Formatter {

    public static String Format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
