package me.siridev.translationsBridge.core.api;

import me.siridev.translationsBridge.core.api.entity.LocaleData;
import me.siridev.translationsBridge.core.api.entity.TranslationFile;
import me.siridev.translationsBridge.core.util.PlaceholderUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class TranslationAPI implements TranslationHandler {

    private final File localesFolder;
    private final Map<String, LocaleData> localesCache = new HashMap<>();

    public TranslationAPI(File dataFolder) {
        this.localesFolder = new File(dataFolder, "locales");
        if (!localesFolder.exists()) localesFolder.mkdir();
    }

    private LocaleData getLocale(String localeId) {
        return localesCache.computeIfAbsent(localeId.toLowerCase(), id -> {
            File localeDir = new File(localesFolder, id);
            return localeDir.exists() ? new LocaleData(id, localeDir) : null;
        });
    }

    @Override
    public String getTranslation(String localeId, String path, String key) {
        LocaleData locale = getLocale(localeId);
        if (locale == null) return null;

        TranslationFile file = locale.getFile(path);
        if (file == null) return null;

        return file.getValue(key);
    }

    @Override
    public String getTranslation(String localeId, String path, String key, Map<String, String> placeholders) {
        String value = getTranslation(localeId, path, key);
        if (value == null) return null;

        return PlaceholderUtil.replace(value, placeholders);
    }
}
