package ru.digitalhabbits.homework1.plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CounterPlugin
        implements PluginInterface {

    private static final String REGEX_PATTERN = "(\\b[a-zA-Z][a-zA-Z.\\-0-9]*\\b)";

    @Nullable
    @Override
    public String apply(@Nonnull String text) {
        List<String> split = Pattern.compile(REGEX_PATTERN)
                .matcher(text)
                .results()
                .map(MatchResult::group)
                .map(w -> w.toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());

        long words = split.size();
        long letters = split.stream()
                .flatMapToInt(String::chars)
                .count();
                //.reduce(0, (subtotal, element) -> subtotal + element.length(), Integer::sum);
        long lines = text.stripTrailing().lines().count();

        return String.join(";", String.valueOf(lines), String.valueOf(words), String.valueOf(letters));
    }
}
