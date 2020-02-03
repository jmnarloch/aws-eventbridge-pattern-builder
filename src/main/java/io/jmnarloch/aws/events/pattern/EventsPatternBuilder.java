package io.jmnarloch.aws.events.pattern;

public interface EventsPatternBuilder extends PathPatternBuilder<EventsPatternBuilder> {

    EventsPattern build();
}
