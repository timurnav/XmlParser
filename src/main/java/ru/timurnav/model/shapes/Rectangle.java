package ru.timurnav.model.shapes;

import ru.timurnav.model.Shape;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "rectangle")
@XmlRootElement
public class Rectangle extends Shape {

    @XmlElement(name = "side")
    private List<Float> sides;

    @Override
    public double square() {
        return sides.get(0) * sides.get(1);
    }
}
