package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "square")
@XmlRootElement
public class Square extends Shape {

    @XmlElement(name = "side")
    private float side;

    public Square() {
    }

    public Square(String xmlString, int number) {
        super(number);
        Square unmarshaled = (Square) convertXmlToObject(xmlString, Square.class);
        this.color = unmarshaled.color;
        this.side= unmarshaled.side;
    }

    public Square(String xmlString) {
        this(xmlString, sequence.incrementAndGet());
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
