/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
