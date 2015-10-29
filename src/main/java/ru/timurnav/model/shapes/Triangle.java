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
    private Float side1, side2, side3;

    public Triangle() {
    }

    public Triangle(String xmlString, int number) {
        super(number);
        Triangle unmarshaled = (Triangle) convertXmlToObject(xmlString, Triangle.class);
        this.color = unmarshaled.color;
        this.side1 = unmarshaled.side1;
        this.side2 = unmarshaled.side2;
        this.side3 = unmarshaled.side3;
    }

    protected void validate() {
        Objects.requireNonNull(color);
        Objects.requireNonNull(side1);
        Objects.requireNonNull(side2);
        Objects.requireNonNull(side3);
    }

    public Triangle(String xmlString) {
        this(xmlString, sequence.incrementAndGet());
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
