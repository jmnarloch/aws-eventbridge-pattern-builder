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
package io.jmnarloch.aws.events.pattern;

import io.jmnarloch.aws.events.matchers.AnythingButMatcher;
import io.jmnarloch.aws.events.matchers.Comparision;
import io.jmnarloch.aws.events.matchers.EqualMatcher;
import io.jmnarloch.aws.events.matchers.ExistsMatcher;
import io.jmnarloch.aws.events.matchers.Matcher;
import io.jmnarloch.aws.events.matchers.NumericMatcher;
import io.jmnarloch.aws.events.matchers.PrefixMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toMap;

final class CompositeEventsPatternBuilder implements EventsPatternBuilder {

    private final CompositeEventsPatternBuilder parent;

    private final Map<String, Collection<Matcher>> matchers;

    private final Map<String, CompositeEventsPatternBuilder> children;

    public CompositeEventsPatternBuilder() {
        this(null);
    }

    public CompositeEventsPatternBuilder(CompositeEventsPatternBuilder parent) {
        this.parent = parent;
        this.matchers = new HashMap<>();
        this.children = new HashMap<>();
    }

    @Override
    public EventsPatternBuilder equal(String attribute, Object value, Object... values) {
        Objects.requireNonNull(attribute);
        requireNotEmpty(attribute);
        Objects.requireNonNull(value);
        Objects.requireNonNull(values);
        final Object[] all = prepend(value, values);
        return addMatcher(attribute, new EqualMatcher(all));
    }

    @Override
    public EventsPatternBuilder prefix(String attribute, String prefix) {
        Objects.requireNonNull(attribute);
        requireNotEmpty(attribute);
        Objects.requireNonNull(prefix);
        return addMatcher(attribute, new PrefixMatcher(prefix));
    }

    @Override
    public EventsPatternBuilder anythingBut(String attribute, Object value, Object... values) {
        Objects.requireNonNull(attribute);
        requireNotEmpty(attribute);
        Objects.requireNonNull(value);
        Objects.requireNonNull(values);
        final Object[] all = prepend(value, values);
        return addMatcher(attribute, new AnythingButMatcher(all));
    }

    @Override
    public EventsPatternBuilder numeric(String attribute, Comparision comparision, Number value) {
        Objects.requireNonNull(attribute);
        requireNotEmpty(attribute);
        Objects.requireNonNull(comparision);
        Objects.requireNonNull(value);
        return addMatcher(attribute, new NumericMatcher(comparision, value));
    }

    @Override
    public EventsPatternBuilder exists(String attribute) {
        Objects.requireNonNull(attribute);
        requireNotEmpty(attribute);
        return addMatcher(attribute, new ExistsMatcher(true));
    }

    @Override
    public EventsPatternBuilder notExists(String attribute) {
        Objects.requireNonNull(attribute);
        requireNotEmpty(attribute);
        return addMatcher(attribute, new ExistsMatcher(false));
    }

    @Override
    public EventsPatternBuilder path(String path) {
        Objects.requireNonNull(path);
        requireNotEmpty(path);
        CompositeEventsPatternBuilder builder = new CompositeEventsPatternBuilder(this);
        this.children.put(path, builder);
        return builder;
    }

    private void requireNotEmpty(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Parameter can not be empty string");
        }
    }

    @Override
    public EventsPatternBuilder parent() {
        if (Objects.isNull(parent)) {
            throw new IllegalStateException("Parent context does not exist.");
        }
        return parent;
    }

    @Override
    public EventsPattern build() {
        return new EventsPattern(buildPath());
    }

    private PathPattern buildPath() {
        final Map<String, PathPattern> paths = children.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, value -> value.getValue().buildPath()));
        return new PathPattern(matchers, paths);
    }

    private EventsPatternBuilder addMatcher(String attribute, Matcher matcher) {
        matchers.put(attribute, List.of(matcher));
        return this;
    }

    private Object[] prepend(Object value, Object[] values) {
        final Object[] results = new Object[values.length + 1];
        results[0] = value;
        System.arraycopy(values, 0, results, 1, values.length);
        return results;
    }
}
