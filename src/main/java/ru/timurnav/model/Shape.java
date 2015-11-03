package ru.timurnav.model;

import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

import static javax.xml.stream.XMLStreamConstants.*;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public abstract class Shape {

    protected final static AtomicInteger sequence = new AtomicInteger(0);

    protected String color;
    protected int number;

    protected Shape(int number, String color) {
        this.number = number;
        this.color = color;
    }

    protected Shape(XMLStreamReader reader) throws XMLStreamException {
        number = sequence.incrementAndGet();
        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
            color = reader.getText();
            if (reader.next() == END_ELEMENT) return;
        }
        throw ExceptionUtils.getExpetionWithMessage(TAGS_CONTENT);
    }

    public abstract double square();

    public String toString() {
        return String.format("%d:%s - %.2f",
                number, color, square());
    }
}
