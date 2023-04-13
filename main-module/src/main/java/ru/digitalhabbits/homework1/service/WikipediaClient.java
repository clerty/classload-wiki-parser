package ru.digitalhabbits.homework1.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import static org.slf4j.LoggerFactory.getLogger;

public class WikipediaClient {
    private static final Logger logger = getLogger(WikipediaClient.class);

    private static final String WIKIPEDIA_SEARCH_URL = "https://en.wikipedia.org/w/api.php";
    private static final String CONTENT_FORMAT = "wikitext";
    private static final String FORMAT_TYPE = "json";
    private static final String ACTION = "parse";

    @Nonnull
    public String search(@Nonnull String searchString) {
        return search(searchString, null);
    }

    @Nonnull
    public String search(@Nonnull String searchString, @Nullable String revision) {
        final URI uri = prepareSearchUrl(searchString, revision);
        final HttpGet request = new HttpGet(uri);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            final int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode)
                throw new HttpResponseException(statusCode, response.getStatusLine().getReasonPhrase());

            final JsonObject json = JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent())).getAsJsonObject();
            final String text = json.get(ACTION).getAsJsonObject()
                    .get(CONTENT_FORMAT).getAsString();
            return MarkdownParser.parseToText(text);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Nonnull
    private URI prepareSearchUrl(@Nonnull String searchString, @Nullable String revision) {
        try {
            final URIBuilder builder = new URIBuilder(WIKIPEDIA_SEARCH_URL)
                    .addParameter("action", ACTION)
                    .addParameter("prop", CONTENT_FORMAT)
                    .addParameter("format", FORMAT_TYPE)
                    .addParameter("formatversion", "2");

            if (revision != null) {
                builder.addParameter("oldid", revision);
            } else {
                builder.addParameter("page", searchString);
            }

            return builder.build();
        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
    }
}
