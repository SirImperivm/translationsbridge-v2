package me.siridev.translationsBridge.platform.velocity.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@SuppressWarnings("all")
public class Formatter {

    public static Component fromAmpersand(String input) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input);
    }

    // Converti stringa con § in componente
    public static Component fromSection(String input) {
        return LegacyComponentSerializer.legacySection().deserialize(input);
    }

    // Converti componente in stringa con & (legacy)
    public static String toAmpersand(Component component) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }
}
