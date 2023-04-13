package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrequencyDictionaryPlugin
        implements PluginInterface {

    private static final String REGEX_PATTERN = "(\\b[a-zA-Z][a-zA-Z.\\-0-9]*\\b)";

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        Stream<String> split = Pattern.compile(REGEX_PATTERN)
                .matcher(text)
                .results()
                .map(MatchResult::group)
                .map(w -> w.toLowerCase(Locale.ROOT));

        return split
                .parallel()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(e -> e.getKey() + " " + e.getValue())
                    .collect(Collectors.joining("\n"));
    }
}
