package ru.digitalhabbits.homework1.service;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.apache.commons.lang3.tuple.Pair.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WikipediaClientTest {

    private final WikipediaClient client = new WikipediaClient();

    @ParameterizedTest
    @MethodSource("generateSearchData")
    void search(Pair<String, String> searchData) {
        final String searchResult = client.search(searchData.getKey());
        assertFalse(searchResult.isBlank());
        assertTrue(searchResult.contains(searchData.getValue()));
    }

    static Stream<Pair<String, String>> generateSearchData() {
        return Stream.of(
                of("Hypertext Transfer Protocol", "Hypertext Transfer Protocol"),
                of("WebSocket", "WebSocket is a computer communications protocol"),
                of("HTML", "Hypertext Markup Language")
        );
    }
}