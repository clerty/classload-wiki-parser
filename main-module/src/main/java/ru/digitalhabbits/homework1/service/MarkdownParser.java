package ru.digitalhabbits.homework1.service;

import lombok.SneakyThrows;
import org.xwiki.component.embed.EmbeddableComponentManager;
import org.xwiki.rendering.converter.Converter;
import org.xwiki.rendering.renderer.printer.DefaultWikiPrinter;
import org.xwiki.rendering.syntax.Syntax;

import javax.annotation.Nonnull;
import java.io.StringReader;

final class MarkdownParser {

    @SneakyThrows
    static String parseToText(@Nonnull String markdown) {
        final EmbeddableComponentManager componentManager = new EmbeddableComponentManager();
        componentManager.initialize(MarkdownParser.class.getClassLoader());
        Converter converter = componentManager.getInstance(Converter.class);

        DefaultWikiPrinter printer = new DefaultWikiPrinter();
        converter.convert(new StringReader(markdown), Syntax.MEDIAWIKI_1_6, Syntax.PLAIN_1_0, printer);
        return printer.getBuffer().toString();
    }
}
