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
    private Float side1, side2;

    public Rectangle() {
    }

    public Rectangle(String xmlString, int number) {
        super(number);
        Rectangle unmarshaled = (Rectangle) convertXmlToObject(xmlString, Rectangle.class);
        this.color = unmarshaled.color;
        this.side1 = unmarshaled.side1;
        this.side2 = unmarshaled.side2;
    }

    public Rectangle(String xmlString) {
        this(xmlString, sequence.incrementAndGet());
    }

    @Override
    protected void validate() {
        Objects.requireNonNull(color);
        Objects.requireNonNull(side1);
        Objects.requireNonNull(side2);
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
