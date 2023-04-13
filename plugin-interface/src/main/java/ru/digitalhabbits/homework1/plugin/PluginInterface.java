package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface PluginInterface {
    @Nullable
    String apply(@Nonnull String text);
}
