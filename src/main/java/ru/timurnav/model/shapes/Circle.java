package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(name = "circle")
@XmlRootElement
public class Circle extends Shape {

    @XmlElement(name = "diameter")
    private float diameter;

    public Circle() {
    }

    public Circle(String xmlString, int number) {
        super(number);
        Circle unmarshaled = (Circle) convertXmlToObject(xmlString, Circle.class);
        this.color = unmarshaled.color;
        this.diameter= unmarshaled.diameter;
    }

    public Circle(String xmlString) {
        this(xmlString, sequence.incrementAndGet());
    }

    @Override
    protected void validate() {
        Objects.requireNonNull(color);
        Objects.requireNonNull(diameter);
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
