package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;
import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.Objects;

import static javax.xml.stream.XMLStreamConstants.*;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public class Rectangle extends Shape {

    private Float side1;
    private Float side2;

    public Rectangle(int number, String color, Float side1, Float side2) {
        super(number, color);
        this.side1 = side1;
        this.side2 = side2;
    }

    public Rectangle(XMLStreamReader reader) throws XMLStreamException {
        super(reader);
        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
            side1 = Float.valueOf(reader.getText());
            if (reader.next() == END_ELEMENT) {
                if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
                    side2 = Float.valueOf(reader.getText());
                    if (reader.next() == END_ELEMENT) return;
                }
            }
        }
        throw ExceptionUtils.getExceptionWithMessage(TAGS_CONTENT);
    }

    @Override
    public double square() {
        return side1 * side2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(side1, rectangle.side1) &&
                Objects.equals(side2, rectangle.side2) &&
                Objects.equals(number, rectangle.number) &&
                Objects.equals(color, rectangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, number, color);
    }
}
