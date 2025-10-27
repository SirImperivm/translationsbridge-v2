package me.siridev.translationsBridge.core.api.entity;

import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("all")
public class TranslationFile {

    private final File file;
    private final YamlFile yaml;

    public TranslationFile(File file) {
        this.file = file;
        this.yaml = new YamlFile(file);
        try {
            yaml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return yaml.contains(key) ? yaml.getString(key) : null;
    }

    public File getFile() {
        return file;
    }
}
