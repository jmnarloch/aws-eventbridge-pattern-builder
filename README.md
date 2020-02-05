#  AWS EventBridge Rule Pattern DSL

> A Java utility library for creating EventBridge Patterns.

## Setup
 
Add the library to your project:
 
```xml
 <dependency>
   <groupId>io.jmnarloch</groupId>
   <artifactId>aws-eventbridge-pattern-builder</artifactId>
   <version>1.0.0</version>
 </dependency>
```

## Usage

Library defines very simple builder pattern for creating an EventBridge Rule patterns.

Examples:

Matching events publish by `aws.ec2`.

```
EventsPattern.builder()
        .equal("source", "aws.ec2")
        .build();
```

Matching events based on the `detail` content.

```
EventsPattern.builder()
    .path("detail")
        .equal("state", "terminated")
        .parent()
    .build();
```

Matching attributes by prefix.

```
EventsPattern.builder()
    .prefix("region", "eu-")
    .build();
```

Matching events that attribute is any value other then specified.

```
EventsPattern.builder()
    .path("detail")
        .anythingBut("state", "initializing")
        .parent()
    .build();
```

Matching the events based on specified number comparision.

```
EventsPattern.builder()
    .path("detail")
        .numeric("c-count", Comparision.GREATER_THAN, 5)
        .numeric("d-count", Comparision.LOWER_THAN, 10)
        .numeric("x-limit", Comparision.EQUAL, 3.018e2)
        .parent()
    .build();
``` 

See also the [documentation](https://docs.aws.amazon.com/eventbridge/latest/userguide/content-filtering-with-event-patterns.html#filtering-numeric-matching) for supported EventBridge operators.

## License

Apache 2.0