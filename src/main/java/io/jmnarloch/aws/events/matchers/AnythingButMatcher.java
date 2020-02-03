package io.jmnarloch.aws.events.matchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AnythingButMatcher extends Matcher {

    private static final String OPERATOR = "anything-but";

    private final Object[] values;

    public AnythingButMatcher(Object[] values) {
        this.values = values;
    }

    public Object[] getValues() {
        return values;
    }

    @Override
    protected Collection<JsonNode> toJson() {
        final List<JsonNode> vals = Arrays.stream(values)
                .map(this::valuesToJson)
                .collect(toList());
        return Collections.singleton(createNode(vals));
    }

    private ObjectNode createNode(List<JsonNode> vals) {
        final ObjectNode node = JsonNodeFactory.instance.objectNode();
        if (vals.size() > 1) {
            final ArrayNode valuesArray = node.putArray(OPERATOR);
            vals.forEach(valuesArray::add);
        } else {
            node.set(OPERATOR, vals.get(0));
        }
        return node;
    }
}
