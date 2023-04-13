package ru.digitalhabbits.homework1.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PluginEngineTest {
    private final PluginEngine engine = new PluginEngine();

    @ParameterizedTest
    @ValueSource(strings = { "Hello, world", "Alex", "E2-E4" })
    void applyPlugin(String text) {
        assertEquals(text, engine.applyPlugin(DummyPlugin.class, text));
    }
}