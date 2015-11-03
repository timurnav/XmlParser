package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;
import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.Objects;

import static javax.xml.stream.XMLStreamConstants.*;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public class Triangle extends Shape {

    private Float side1;
    private Float side2;
    private Float side3;

    public Triangle(int number, String color, Float side1, Float side2, Float side3) {
        super(number, color);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Triangle(XMLStreamReader reader) throws XMLStreamException {
        super(reader);
        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
            side1 = Float.valueOf(reader.getText());
            if (reader.next() == END_ELEMENT) {
                if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
                    side2 = Float.valueOf(reader.getText());
                    if (reader.next() == END_ELEMENT) {
                        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
                            side3 = Float.valueOf(reader.getText());
                            if (reader.next() == END_ELEMENT) return;
                        }
                    }
                }
            }
        }
        throw ExceptionUtils.getExpetionWithMessage(TAGS_CONTENT);
    }

    @Override
    public double square() {
        float p = (side1 + side2 + side3) / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(side1, triangle.side1) &&
                Objects.equals(side2, triangle.side2) &&
                Objects.equals(side3, triangle.side3) &&
                Objects.equals(number, triangle.number) &&
                Objects.equals(color, triangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, side3, number, color);
    }
}
