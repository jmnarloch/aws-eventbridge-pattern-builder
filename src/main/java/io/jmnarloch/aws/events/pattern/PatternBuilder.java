package io.jmnarloch.aws.events.pattern;

import io.jmnarloch.aws.events.matchers.Comparision;

public interface PatternBuilder<T extends PatternBuilder<T>> {

    T path(String path);

    T equal(String attribute, Object value, Object... others);

    T prefix(String attribute, String prefix);

    T anythingBut(String attribute, Object value, Object... others);

    T numeric(String attribute, Comparision comparision, Number value);

    T exists(String attribute);

    T notExists(String attribute);
}
