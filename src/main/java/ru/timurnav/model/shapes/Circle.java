package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "circle")
@XmlRootElement
public class Circle extends Shape {

    @XmlElement(name = "diameter")
    private float diameter;

    public Circle() {
    }

    @Override
    public double square() {
        return Math.PI * diameter * diameter;
    }
}
