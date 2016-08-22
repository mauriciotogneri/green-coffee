package gherkin;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import gherkin.ast.Location;
import gherkin.deps.com.google.gson.Gson;

public class GherkinDialectProvider implements IGherkinDialectProvider {
    private static Map<String, Map<String, List<String>>> DIALECTS;
    private final String default_dialect_name;

    static {
        Gson gson = new Gson();
        try {
            Reader dialects = new InputStreamReader(GherkinDialectProvider.class.getResourceAsStream("/gherkin/gherkin-languages.json"), "UTF-8");
            DIALECTS = gson.fromJson(dialects, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GherkinDialectProvider(String default_dialect_name) {
        this.default_dialect_name = default_dialect_name;
    }

    public GherkinDialectProvider() {
        this("en");
    }

    public GherkinDialect getDefaultDialect() {
        return getDialect(default_dialect_name, null);
    }

    @Override
    public GherkinDialect getDialect(String language, Location location) {
        Map<String, List<String>> map = DIALECTS.get(language);
        if (map == null) {
            throw new ParserException.NoSuchLanguageException(language, location);
        }

        return new GherkinDialect(language, map);
    }
}
