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

import io.jmnarloch.aws.events.matchers.Comparision;
import org.junit.jupiter.api.Test;

class Demo {

    @Test
    public void run() {

        EventsPattern.builder()
                .equal("source", "aws.ec2")
                .build();

        EventsPattern.builder()
                .equal("source", "aws.ec2")
                .equal("detail-type", "EC2 Instance State-change Notification")
                .build();

        EventsPattern.builder()
                .equal("source", "aws.ec2")
                .equal("detail-type", "EC2 Instance State-change Notification")
                .path("detail")
                .equal("state", "terminated")
                .parent()
                .build();

        EventsPattern.builder()
                .equal("source", "aws.ec2", "aws.fargate")
                .build();

        EventsPattern.builder()
                .prefix("time", "2017-10-02")
                .build();

        EventsPattern.builder()
                .equal("source", "aws.autoscaling")
                .prefix("region", "eu-")
                .build();

        EventsPattern.builder()
                .path("detail")
                .anythingBut("state", "initializing")
                .parent()
                .build();

        EventsPattern.builder()
                .path("detail")
                .numeric("c-count", Comparision.GREATER_THAN, 5)
                .numeric("d-count", Comparision.LOWER_THAN, 10)
                .numeric("x-limit", Comparision.EQUAL, 3.018e2)
                .parent()
                .build();

        EventsPattern.builder()
                .path("detail")
                .notExists("c-count")
                .parent()
                .build();
    }
}