package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "square")
@XmlRootElement
public class Square extends Shape {

    @XmlElement(name = "side")
    private float a;

    @Override
    public double square() {
        return a * a;
    }
}
