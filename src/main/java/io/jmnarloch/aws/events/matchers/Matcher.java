package io.jmnarloch.aws.events.matchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

public abstract class Matcher {

    protected abstract Collection<JsonNode> toJson();

    protected JsonNode valuesToJson(Object value) {
        if (value == null) {
            return JsonNodeFactory.instance.nullNode();
        } else if (value instanceof Integer) {
            return JsonNodeFactory.instance.numberNode((Integer) value);
        } else if (value instanceof Long) {
            return JsonNodeFactory.instance.numberNode((Long) value);
        } else if (value instanceof Short) {
            return JsonNodeFactory.instance.numberNode((Short) value);
        } else if (value instanceof Byte) {
            return JsonNodeFactory.instance.numberNode((Byte) value);
        } else if (value instanceof Float) {
            return JsonNodeFactory.instance.numberNode((Float) value);
        } else if (value instanceof Double) {
            return JsonNodeFactory.instance.numberNode((Double) value);
        } else if (value instanceof BigInteger) {
            return JsonNodeFactory.instance.numberNode((BigInteger) value);
        } else if (value instanceof BigDecimal) {
            return JsonNodeFactory.instance.numberNode((BigDecimal) value);
        } else {
            return JsonNodeFactory.instance.textNode(value.toString());
        }
    }

    public static void appendJson(ArrayNode node, Collection<Matcher> matchers) {
        matchers.forEach(matcher -> matcher.toJson().forEach(node::add));
    }
}
