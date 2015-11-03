package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;
import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.Objects;

import static javax.xml.stream.XMLStreamConstants.*;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public class Square extends Shape {

    private float side;

    public Square(int number, String color, float side) {
        super(number, color);
        this.side = side;
    }

    public Square(XMLStreamReader reader) throws XMLStreamException {
        super(reader);
        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
            side = Float.valueOf(reader.getText());
            if (reader.next() == END_ELEMENT) return;
        }
        throw ExceptionUtils.getExpetionWithMessage(TAGS_CONTENT);
    }

    @Override
    public double square() {
        return side * side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(side, square.side) &&
                Objects.equals(number, square.number) &&
                Objects.equals(color, square.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, number, color);
    }
}
