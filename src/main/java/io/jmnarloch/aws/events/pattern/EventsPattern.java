package io.jmnarloch.aws.events.pattern;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class EventsPattern {

    private final PathPattern root;

    EventsPattern(PathPattern root) {
        this.root = root;
    }

    public String toJson() {
        final ObjectNode result = JsonNodeFactory.instance.objectNode();
        root.appendJson(result);
        return result.toString();
    }

    public static EventsPatternBuilder builder() {
        return new CompositeEventsPatternBuilder();
    }
}
