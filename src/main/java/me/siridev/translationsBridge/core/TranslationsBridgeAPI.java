package me.siridev.translationsBridge.core;

import me.siridev.translationsBridge.core.api.TranslationHandler;

@SuppressWarnings("all")
public class TranslationsBridgeAPI {

    private static TranslationHandler handler;

    public static void SetHandler(TranslationHandler instance) {
        handler = instance;
    }

    public static TranslationHandler Get() {
        if (handler == null) throw new IllegalStateException("TranslationHandler is not initialized yet!");
        return handler;
    }
}
