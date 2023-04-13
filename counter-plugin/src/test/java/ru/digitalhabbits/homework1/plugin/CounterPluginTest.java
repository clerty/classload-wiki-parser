package ru.digitalhabbits.homework1.plugin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.digitalhabbits.homework1.plugin.TestData.TestDataHolder;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CounterPluginTest {
    private final CounterPlugin plugin = new CounterPlugin();

    @ParameterizedTest
    @MethodSource("testDataGenerator")
    void apply(TestDataHolder testData) {
        final String result = plugin.apply(testData.getText());
        assertNotNull(result);
        final String[] count = result.split(";");
        assertEquals(3, count.length);
        assertEquals(testData.getLines(), Integer.parseInt(count[0]));
        assertEquals(testData.getWords(), Integer.parseInt(count[1]));
        assertEquals(testData.getLetters(), Integer.parseInt(count[2]));
    }

    static Stream<TestDataHolder> testDataGenerator() {
        return Stream.of(
                new TestDataHolder(TestData.TEXT1, 3, 8, 32),
                new TestDataHolder(TestData.TEXT2, 1, 6, 28),
                new TestDataHolder(TestData.TEXT3, 4, 23, 92),
                new TestDataHolder(TestData.TEXT4, 3, 57, 309)
        );
    }
}