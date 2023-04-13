package ru.digitalhabbits.homework1.service;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework1.plugin.PluginInterface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PluginLoaderTest {
    private static final String PLUGIN_DIR = "src/test/resources/plugins";

    private final PluginLoader loader = new PluginLoader();

    @Test
    void loadPlugins() {
        final List<Class<? extends PluginInterface>> classes =
                loader.loadPlugins(PLUGIN_DIR);
        assertEquals(1, classes.size());
        assertEquals("TestPlugin", classes.get(0).getSimpleName());
    }
}