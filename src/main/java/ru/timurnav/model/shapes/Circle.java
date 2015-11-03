package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;
import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.Objects;

import static javax.xml.stream.XMLStreamConstants.*;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public class Circle extends Shape {

    private float diameter;

    public Circle(int number, String color, float diameter) {
        super(number, color);
        this.diameter = diameter;
    }

    public Circle(XMLStreamReader reader) throws XMLStreamException {
        super(reader);
        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
            diameter = Float.valueOf(reader.getText());
            if (reader.next() == END_ELEMENT) return;
        }
        throw ExceptionUtils.getExpetionWithMessage(TAGS_CONTENT);
    }

    @Override
    public double square() {
        return Math.PI * diameter * diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Objects.equals(diameter, circle.diameter) &&
                Objects.equals(number, circle.number) &&
                Objects.equals(color, circle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diameter, number, color);
    }
}
