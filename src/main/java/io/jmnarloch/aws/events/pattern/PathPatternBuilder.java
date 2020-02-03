package io.jmnarloch.aws.events.pattern;

public interface PathPatternBuilder<T extends PathPatternBuilder<T>> extends PatternBuilder<T> {

    T parent();
}
