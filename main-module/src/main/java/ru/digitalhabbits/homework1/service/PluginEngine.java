package ru.digitalhabbits.homework1.service;

import ru.digitalhabbits.homework1.plugin.PluginInterface;

import javax.annotation.Nonnull;

public class PluginEngine {

    @Nonnull
    public  <T extends PluginInterface> String applyPlugin(@Nonnull Class<T> cls, @Nonnull String text) {
        try {
            T plugin = cls.getConstructor().newInstance();
            String result = plugin.apply(text);
            return result == null ? "" : result;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
