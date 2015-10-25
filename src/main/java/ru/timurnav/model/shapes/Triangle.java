package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "triangle")
@XmlRootElement
public class Triangle extends Shape {

    @XmlElement(name = "side")
    private List<Float> sides;

    @Override
    public double square() {
        float a = sides.get(0);
        float b = sides.get(1);
        float c = sides.get(2);
        float p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
