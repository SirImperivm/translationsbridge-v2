package me.siridev.translationsBridge.core.util;

import java.util.Map;

@SuppressWarnings("all")
public class PlaceholderUtil {

    public static String replace(String text, Map<String, String> placeholders) {
        if (text == null || placeholders == null || placeholders.isEmpty()) return text;
        String result = text;
        for (Map.Entry<String, String> entry :placeholders.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
