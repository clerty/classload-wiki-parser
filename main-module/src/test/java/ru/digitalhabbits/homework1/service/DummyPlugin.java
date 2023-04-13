package ru.digitalhabbits.homework1.service;

import ru.digitalhabbits.homework1.plugin.PluginInterface;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DummyPlugin
        implements PluginInterface {

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        return text;
    }
}
