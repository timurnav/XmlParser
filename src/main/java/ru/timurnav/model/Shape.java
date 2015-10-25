package ru.timurnav.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Shape {

    private final static AtomicInteger sequence = new AtomicInteger(0);

    @XmlElement(name = "color")
    private String color;

    public abstract double square();

    @Override
    public String toString() {
        return String.format("%d:%s - %.2f",
                sequence.incrementAndGet(), color, square());
    }
}
