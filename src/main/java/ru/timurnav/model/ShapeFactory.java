package ru.timurnav.model;

import ru.timurnav.model.shapes.Circle;
import ru.timurnav.model.shapes.Rectangle;
import ru.timurnav.model.shapes.Square;
import ru.timurnav.model.shapes.Triangle;
import ru.timurnav.util.XmlParserException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static ru.timurnav.util.ExceptionUtils.ExceptionType.TAG;

public class ShapeFactory {

    public static Shape getShape(ShapeType type, XMLStreamReader reader) throws XMLStreamException {
        switch (type) {
            case CIRCLE:
                return new Circle(reader);
            case RECTANGLE:
                return new Rectangle(reader);
            case SQUARE:
                return new Square(reader);
            case TRIANGLE:
                return new Triangle(reader);
            default:
                throw new XmlParserException(TAG);
        }
    }

}
