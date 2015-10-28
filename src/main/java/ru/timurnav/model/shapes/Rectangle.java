package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;

@XmlType(name = "rectangle")
@XmlRootElement
public class Rectangle extends Shape {

    @XmlElement(name = "side")
    private List<Float> sides;

    public Rectangle() {
    }

    public Rectangle(String xmlString, int number) {
        super(number);
        Rectangle unmarshaled = (Rectangle) convertXmlToObject(xmlString, Rectangle.class);
        this.color = unmarshaled.color;
        this.sides = unmarshaled.sides;
    }

    public Rectangle(String xmlString) {
        this(xmlString, sequence.incrementAndGet());
    }

    @Override
    public double square() {
        return sides.get(0) * sides.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(sides, rectangle.sides) &&
                Objects.equals(number, rectangle.number) &&
                Objects.equals(color, rectangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sides, number, color);
    }
}
