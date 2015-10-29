package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;
import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Objects;

@XmlType(name = "triangle")
@XmlRootElement
public class Triangle extends Shape {

    @XmlElement(name = "side")
    private List<Float> sides;

    public Triangle() {
    }

    public Triangle(String xmlString, int number) {
        super(number);
        Triangle unmarshaled = (Triangle) convertXmlToObject(xmlString, Triangle.class);
        this.color = unmarshaled.color;
        this.sides = unmarshaled.sides;
    }

    protected void validate() {
        Objects.requireNonNull(color);
        Objects.requireNonNull(sides.get(0));
        Objects.requireNonNull(sides.get(1));
        Objects.requireNonNull(sides.get(2));
    }

    public Triangle(String xmlString) {
        this(xmlString, sequence.incrementAndGet());
    }

    @Override
    public double square() {
        float a = sides.get(0);
        float b = sides.get(1);
        float c = sides.get(2);
        float p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(sides, triangle.sides) &&
                Objects.equals(number, triangle.number) &&
                Objects.equals(color, triangle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sides, number, color);
    }
}
