package me.siridev.translationsBridge.core.util;

import org.simpleyaml.configuration.file.YamlFile;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("all")
public class YamlLoader {

    public static YamlFile load(File file) {
        YamlFile yaml = new YamlFile(file);
        try {
            yaml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yaml;
    }
}
