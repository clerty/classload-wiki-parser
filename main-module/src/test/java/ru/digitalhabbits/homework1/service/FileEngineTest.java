package ru.digitalhabbits.homework1.service;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.apache.commons.lang3.tuple.Pair.of;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileEngineTest {

    private final FileEngine fileEngine = new FileEngine();

    @ParameterizedTest
    @MethodSource("generateWriteData")
    void writeFile(Pair<String, String> writeData) {
        assertTrue(fileEngine.writeToFile(writeData.getKey(), writeData.getKey(), writeData.getValue()));
    }

    static Stream<Pair<String, String>> generateWriteData() {
        return Stream.of(
                of("Hypertext Transfer Protocol", "plugin1"),
                of("WebSocket", "plugin2"),
                of("HTML", "plugin3")
        );
    }
}
