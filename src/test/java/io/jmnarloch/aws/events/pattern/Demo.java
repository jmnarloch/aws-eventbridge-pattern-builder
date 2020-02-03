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
                .prefix("time", "eu-")
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