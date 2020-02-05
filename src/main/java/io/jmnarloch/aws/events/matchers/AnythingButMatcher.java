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
