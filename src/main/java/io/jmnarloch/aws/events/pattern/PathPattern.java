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

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jmnarloch.aws.events.matchers.Matcher;

import java.util.Collection;
import java.util.Map;

final class PathPattern {

    private final Map<String, Collection<Matcher>> matchers;

    private final Map<String, PathPattern> paths;

    PathPattern(Map<String, Collection<Matcher>> matchers, Map<String, PathPattern> paths) {
        this.matchers = Map.copyOf(matchers);
        this.paths = Map.copyOf(paths);
    }

    void appendJson(ObjectNode node) {
        matchers.forEach((key, matchers) -> Matcher.appendJson(node.putArray(key), matchers));
        paths.forEach((key, path) -> path.appendJson(node.putObject(key)));
    }
}
