package ru.timurnav.model;

import ru.timurnav.util.XmlParserException;

import java.util.Arrays;

import static ru.timurnav.util.ExceptionUtils.ExceptionType.TAG;

public enum ShapeType {
    CIRCLE("circle"),
    TRIANGLE("triangle"),
    SQUARE("square"),
    RECTANGLE("rectangle"),
    ROOT_SHAPE("shapes");

    private final String eventName;

    ShapeType(String eventName) {
        this.eventName = eventName;
    }

    public boolean matchEventName(String eventName) {
        return this.eventName.equals(eventName);
    }

    public static ShapeType getShapeTypeByEvent(String eventName) {
        return Arrays.asList(ShapeType.values())
                .stream()
                .filter(t -> t.matchEventName(eventName))
                .findAny()
                .orElseThrow(() -> new XmlParserException(TAG));
    }
}
