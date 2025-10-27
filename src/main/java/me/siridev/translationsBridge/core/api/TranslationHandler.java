package me.siridev.translationsBridge.core.api;

import java.util.Map;

@SuppressWarnings("all")
public interface TranslationHandler {

    /**
     * Returns the pure translations without placeholders replaced.
     * @param localeId
     * @param path
     * @param key
     * @return
     */
    String getTranslation(String localeId, String path, String key);

    /**
     * Returns the translations with the placeholders replaced.
     * @param localeId
     * @param path
     * @param key
     * @param placeholders
     * @return
     */
    String getTranslation(String localeId, String path, String key, Map<String, String> placeholders);
}
