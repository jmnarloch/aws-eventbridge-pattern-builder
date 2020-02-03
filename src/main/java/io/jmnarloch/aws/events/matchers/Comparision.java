package io.jmnarloch.aws.events.matchers;

public enum Comparision {
    EQUAL("="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LOWER_THAN("<"),
    LOWER_THAN_OR_EQUAL("<=");

    private final String symbol;

    Comparision(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
