package io.jmnarloch.aws.events.pattern;

import io.jmnarloch.aws.events.matchers.Comparision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventsPatternTest {

    @Test
    public void shouldMatchString() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", "sample")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[\"sample\"]}", pattern.toJson());
    }

    @Test
    public void shouldMatchInteger() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 42)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[42]}", pattern.toJson());
    }

    @Test
    public void shouldMatchLong() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 2147483648L)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[2147483648]}", pattern.toJson());
    }

    @Test
    public void shouldMatchFloat() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 3.14F)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[3.14]}", pattern.toJson());
    }

    @Test
    public void shouldMatchDouble() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 3.14)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[3.14]}", pattern.toJson());
    }

    @Test
    public void shouldMatchArrayOfString() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", "one", "two", "three")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[\"one\",\"two\",\"three\"]}", pattern.toJson());
    }

    @Test
    public void shouldMatchArrayOfIntegers() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 1, 2, 3)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[1,2,3]}", pattern.toJson());
    }

    @Test
    public void shouldMatchArrayOfFloats() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 1F, 2F, 3F)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[1.0,2.0,3.0]}", pattern.toJson());
    }

    @Test
    public void shouldMatchArrayOfDoubles() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .equal("val", 1D, 2D, 3D)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[1.0,2.0,3.0]}", pattern.toJson());
    }

    @Test
    public void shouldMatchPrefix() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .prefix("val", "event")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"prefix\":\"event\"}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButString() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", "event")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":\"event\"}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButInteger() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", 1)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":1}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButFloat() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", 1F)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":1.0}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButArrayOfStrings() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", "one", "two", "three")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":[\"one\",\"two\",\"three\"]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButArrayOfIntegers() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", 1, 2, 3)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":[1,2,3]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButArrayOfFloat() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", 1F, 2F, 3F)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":[1.0,2.0,3.0]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButArrayOfDouble() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", 1D, 2D, 3D)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":[1.0,2.0,3.0]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchAnythingButDouble() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .anythingBut("val", 1D)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"anything-but\":1.0}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchNumericEqual() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .numeric("val", Comparision.EQUAL, 42)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"numeric\":[\"=\",42]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchNumericGreaterThan() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .numeric("val", Comparision.GREATER_THAN, 42)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"numeric\":[\">\",42]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchNumericLowerThan() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .numeric("val", Comparision.LOWER_THAN, 42)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"numeric\":[\"<\",42]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchNumericGreaterThanOrEqual() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .numeric("val", Comparision.GREATER_THAN_OR_EQUAL, 42)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"numeric\":[\">=\",42]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchNumericLowerThanOrEqual() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .numeric("val", Comparision.LOWER_THAN_OR_EQUAL, 42)
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"numeric\":[\"<=\",42]}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchExistingAttribute() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .exists("val")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"exists\":true}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchNotExistingAttribute() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .notExists("val")
                .build();

        assertNotNull(pattern);
        assertEquals("{\"val\":[{\"exists\":false}]}", pattern.toJson());
    }

    @Test
    public void shouldMatchChildNodeString() {

        // given
        final EventsPattern pattern = EventsPattern.builder()
                .path("inner")
                    .equal("val", "sample")
                    .parent()
                .build();

        assertNotNull(pattern);
        assertEquals("{\"inner\":{\"val\":[\"sample\"]}}", pattern.toJson());
    }
}