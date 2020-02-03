package io.jmnarloch.aws.events.matchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collection;
import java.util.Collections;

public class ExistsMatcher extends Matcher {

    private static final String OPERATOR = "exists";

    private final boolean exists;

    public ExistsMatcher(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    @Override
    protected Collection<JsonNode> toJson() {
        final ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put(OPERATOR, exists);
        return Collections.singleton(node);
    }
}
