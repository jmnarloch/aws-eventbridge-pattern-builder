package io.jmnarloch.aws.events.matchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collection;
import java.util.Collections;

public class NumericMatcher extends Matcher {

    private static final String OPERATOR = "numeric";

    private final Comparision comparision;

    private final Number value;

    public NumericMatcher(Comparision comparision, Number value) {
        this.comparision = comparision;
        this.value = value;
    }

    public Comparision getComparision() {
        return comparision;
    }

    public Number getValue() {
        return value;
    }

    @Override
    protected Collection<JsonNode> toJson() {
        final ObjectNode node = JsonNodeFactory.instance.objectNode();
        final ArrayNode values = JsonNodeFactory.instance.arrayNode();
        values.add(comparision.getSymbol());
        values.add(valuesToJson(value));
        node.put(OPERATOR, values);
        return Collections.singleton(node);
    }
}
