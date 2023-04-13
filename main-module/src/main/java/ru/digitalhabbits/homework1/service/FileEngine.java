package ru.digitalhabbits.homework1.service;

import lombok.SneakyThrows;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Arrays.stream;
import static org.slf4j.LoggerFactory.getLogger;

public class FileEngine {
    private static final Logger logger = getLogger(FileEngine.class);
    private static final String RESULT_FILE_PATTERN = "%s/%s/results-%s.txt";
    private static final String RESULT_DIR_PATTERN = "%s/%s";
    private static final String RESULT_DIR = "results";
    private static final String RESULT_EXT = "txt";

    @SneakyThrows
    public boolean writeToFile(@Nonnull String text, @Nonnull String searchString, @Nonnull String pluginName) {
        Files.createDirectories(Paths.get(String.format(RESULT_DIR_PATTERN, RESULT_DIR, searchString)));
        final Path resultFile = Paths.get(String.format(RESULT_FILE_PATTERN, RESULT_DIR, searchString, pluginName));
        Files.write(resultFile, text.getBytes(StandardCharsets.UTF_8));
        logger.info("Created '{}' result file", resultFile.getFileName());
        return Files.exists(resultFile);
    }

    public void cleanResultDir(@Nonnull String searchString) {
        final File resultDir = new File(RESULT_DIR + "/" + searchString);
        if (resultDir.exists()) {
            stream(resultDir.list((dir, name) -> name.endsWith(RESULT_EXT)))
                    .forEach(fileName -> new File(resultDir + "/" + fileName).delete());
        }
    }
}
