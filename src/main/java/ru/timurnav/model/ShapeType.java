package ru.timurnav.model;

import ru.timurnav.model.shapes.Circle;
import ru.timurnav.model.shapes.Rectangle;
import ru.timurnav.model.shapes.Square;
import ru.timurnav.model.shapes.Triangle;

public enum ShapeType {
    CIRCLE("<circle>", "</circle>", Circle.class),
    TRIANGLE("<triangle>", "</triangle>", Triangle.class),
    SQUARE("<square>", "</square>", Square.class),
    RECTANGLE("<rectangle>", "</rectangle>", Rectangle.class),
    ROOT_SHAPE("<shapes>", "</shapes>", Shape.class);

    private final String openTag;
    private final String closeTag;
    private final Class clazz;

    ShapeType(String openTag, String closeTag, Class clazz) {
        this.openTag = openTag;
        this.closeTag = closeTag;
        this.clazz = clazz;
    }

    public Class getShapesClass() {
        return clazz;
    }

    public String getOpenTag() {
        return openTag;
    }

    public String getCloseTag() {
        return closeTag;
    }

    public boolean matchOpenTag(String openTag){
        return this.openTag.equals(openTag);
    }

    public boolean mismatchCloseTag(String closeTag){
        return !this.closeTag.equals(closeTag);
    }

    public boolean matchBothTags(String xmlString){
        return xmlString.startsWith(openTag) && xmlString.endsWith(closeTag);
    }

    public static ShapeType getShapeTypeByOpenTag(String openTag) {
        if (CIRCLE.matchOpenTag(openTag)) return CIRCLE;
        if (TRIANGLE.matchOpenTag(openTag)) return TRIANGLE;
        if (RECTANGLE.matchOpenTag(openTag)) return RECTANGLE;
        if (SQUARE.matchOpenTag(openTag)) return SQUARE;
        throw new IllegalArgumentException("Incorrect open Tag");
    }

    public static Class getClassByXml(String xmlString) {
        if (CIRCLE.matchBothTags(xmlString)) return CIRCLE.getShapesClass();
        if (TRIANGLE.matchBothTags(xmlString)) return TRIANGLE.getShapesClass();
        if (RECTANGLE.matchBothTags(xmlString)) return RECTANGLE.getShapesClass();
        if (SQUARE.matchBothTags(xmlString)) return SQUARE.getShapesClass();
        throw new IllegalArgumentException("Incorrect XmlString");
    }

}
