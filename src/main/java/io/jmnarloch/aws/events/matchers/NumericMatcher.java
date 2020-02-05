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
