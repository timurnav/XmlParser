package ru.timurnav.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Shape {

    private final static AtomicInteger sequence = new AtomicInteger(0);

    @XmlElement(name = "color")
    protected String color;
    protected int number;

    public Shape() {
        number = sequence.incrementAndGet();
    }

    public Shape(String color, int number) {
        this.color = color;
        this.number = number;
    }

    public abstract double square();

    public String toString() {
        return String.format("%d:%s - %.2f",
                number, color, square());
    }
}
