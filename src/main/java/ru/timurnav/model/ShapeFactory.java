package ru.timurnav.model;

import ru.timurnav.model.shapes.Circle;
import ru.timurnav.model.shapes.Rectangle;
import ru.timurnav.model.shapes.Square;
import ru.timurnav.model.shapes.Triangle;

public class ShapeFactory {

    public static Shape getShape(ShapeType type, String xmlString) {
        switch (type){
            case CIRCLE: return new Circle(xmlString);
            case RECTANGLE: return new Rectangle(xmlString);
            case SQUARE: return new Square(xmlString);
            case TRIANGLE: return new Triangle(xmlString);
            default: return null;
        }
    }

}
