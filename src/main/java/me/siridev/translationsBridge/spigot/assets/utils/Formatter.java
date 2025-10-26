package me.siridev.translationsBridge.spigot.assets.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Formatter {

    public static String Translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String Translate(String string, Map<String, String> placeholders) {
        if (string == null) return "";
        if (placeholders == null || placeholders.isEmpty()) return Translate(string);

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            string = string.replace(key, value);
        }

        return Translate(string);
    }

    public static List<String> Translate(List<String> list) {
        return list.stream().map(Formatter::Translate).toList();
    }

    public static List<String> Translate(List<String> list, Map<String, String> placeholders) {
        return list.stream().map(string -> Translate(string, placeholders)).toList();
    }
}
