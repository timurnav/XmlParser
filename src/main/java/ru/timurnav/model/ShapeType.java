package ru.timurnav.model;

import ru.timurnav.xmlReader.ExceptionUtils;

import java.util.Arrays;

import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.OPEN_TAG;

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

    public boolean matchEventName(String eventName){
        return this.eventName.equals(eventName);
    }

    public static ShapeType getShapeTypeByOpenTag(String eventName) {
        return Arrays.asList(ShapeType.values())
                .stream()
                .filter(t -> t.matchEventName(eventName))
                .findAny()
                .orElseThrow(() -> ExceptionUtils.getExpetionWithMessage(OPEN_TAG));
    }

    public static ShapeType getShapeTypeByXmlString(String xmlString) {
        String tmp = xmlString.substring(xmlString.indexOf("<"), xmlString.indexOf(">"));
        System.out.println(tmp);
        return getShapeTypeByOpenTag(tmp);
    }
}
