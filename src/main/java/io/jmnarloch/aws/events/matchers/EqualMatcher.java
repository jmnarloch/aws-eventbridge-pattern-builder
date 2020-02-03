package io.jmnarloch.aws.events.matchers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class EqualMatcher extends Matcher {

    private final Object[] values;

    public EqualMatcher(Object[] values) {
        this.values = values;
    }

    public Object[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    @Override
    protected Collection<JsonNode> toJson() {
        return Arrays.stream(values)
                .map(this::valuesToJson)
                .collect(toList());
    }
}
