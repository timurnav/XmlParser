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

    public Rectangle(String color, int number, List<Float> sides) {
        super(color, number);
        this.sides = sides;
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
        return Objects.hash(sides);
    }
}
