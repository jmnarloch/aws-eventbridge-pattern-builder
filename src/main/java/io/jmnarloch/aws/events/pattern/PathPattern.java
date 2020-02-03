package io.jmnarloch.aws.events.pattern;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jmnarloch.aws.events.matchers.Matcher;

import java.util.Collection;
import java.util.Map;

final class PathPattern {

    private final Map<String, Collection<Matcher>> matchers;

    private final Map<String, PathPattern> paths;

    PathPattern(Map<String, Collection<Matcher>> matchers, Map<String, PathPattern> paths) {
        this.matchers = Map.copyOf(matchers);
        this.paths = Map.copyOf(paths);
    }

    void appendJson(ObjectNode node) {
        matchers.forEach((key, matchers) -> Matcher.appendJson(node.putArray(key), matchers));
        paths.forEach((key, path) -> path.appendJson(node.putObject(key)));
    }
}
