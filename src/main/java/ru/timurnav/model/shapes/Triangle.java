package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

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

    public Triangle(String color, int number, List<Float> sides) {
        super(color, number);
        this.sides = sides;
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
        return Objects.hash(sides);
    }
}
