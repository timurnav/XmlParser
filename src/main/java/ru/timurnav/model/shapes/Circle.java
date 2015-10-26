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

    public Circle(String color, int number, float diameter) {
        super(color, number);
        this.diameter = diameter;
    }

    public Circle() {
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
        return Objects.hash(diameter);
    }
}
