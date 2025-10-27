package me.siridev.translationsBridge.core.api.entity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class LocaleData {

    private final String localeId;
    private final File localeFolder;
    private final Map<String, TranslationFile> filesCache = new HashMap<>();

    public LocaleData(String localeId, File localeFolder) {
        this.localeId = localeId;
        this.localeFolder = localeFolder;
    }

    public String getLocaleId() {
        return localeId;
    }

    public TranslationFile getFile(String relativePath) {
        return filesCache.computeIfAbsent(relativePath, path -> {
            File file = new File(localeFolder, path);
            if (!file.exists()) return null;
            return new TranslationFile(file);
        });
    }
}
