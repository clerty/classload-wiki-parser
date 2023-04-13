package ru.digitalhabbits.homework1.plugin;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.digitalhabbits.homework1.plugin.TestData.TestDataHolder;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FrequencyDictionaryPluginTest {
    private static final Pattern SPLITTER = Pattern.compile("(\\S+)\\s+(\\d+)");
    private final FrequencyDictionaryPlugin plugin = new FrequencyDictionaryPlugin();

    @ParameterizedTest
    @MethodSource("testDataGenerator")
    void apply(TestDataHolder testData) {
        final String result = plugin.apply(testData.getText());
        assertNotNull(result);

        final Map<String, Integer> wordFrequency = testData.getFrequency();
        for (String line: result.split("\n")) {
            final Pair<String, Integer> data = splitLine(line);
            final Integer frequency = wordFrequency.get(data.getKey());
            assertEquals(frequency, data.getValue(),
                    () -> format("Word frequency %s %d not match actual frequency %d", data.getKey(), data.getValue(), frequency));
        }
    }

    private Pair<String, Integer> splitLine(@Nonnull String line) {
        final Matcher matcher = SPLITTER.matcher(line);
        if (matcher.find()) {
            return Pair.of(matcher.group(1), parseInt(matcher.group(2)));
        }
        throw new IllegalArgumentException("Result string not match pattern");
    }

    static Stream<TestDataHolder> testDataGenerator() {
        return Stream.of(
                new TestDataHolder(
                        TestData.TEXT1,
                        new HashMap<>() {{
                            put("peter", 3);
                            put("paul", 3);
                            put("come", 2);
                            put("fly", 2);
                            put("called", 2);
                            put("back", 2);
                            put("one", 2);
                            put("away", 2);
                            put("a", 1);
                            put("wall", 1);
                            put("two", 1);
                            put("on", 1);
                            put("birds", 1);
                            put("sitting", 1);
                            put("little", 1);
                        }}),
                new TestDataHolder(
                        TestData.TEXT2,
                        new HashMap<>() {{
                            put("fun", 2);
                            put("is", 2);
                            put("fall", 2);
                            put("i", 2);
                            put("the", 1);
                            put("see", 1);
                            put("like", 1);
                            put("to", 1);
                            put("run", 1);
                            put("sun", 1);
                        }}
                )
        );
    }
}