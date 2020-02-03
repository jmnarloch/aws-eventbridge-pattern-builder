package io.jmnarloch.aws.events.matchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collection;
import java.util.Collections;

public class PrefixMatcher extends Matcher {

    private static final String OPERATOR = "prefix";

    private final String prefix;

    public PrefixMatcher(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    protected Collection<JsonNode> toJson() {
        final ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put(OPERATOR, prefix);
        return Collections.singleton(node);
    }
}
