package ru.timurnav.model;

import ru.timurnav.util.XmlParserException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static javax.xml.stream.XMLStreamConstants.*;
import static ru.timurnav.util.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public abstract class Shape {

    protected final Logger log = Logger.getLogger(getClass().getName());

    protected final static AtomicInteger sequence = new AtomicInteger(0);

    protected String color;
    protected int number;

    protected Shape(int number, String color) {
        this.number = number;
        this.color = color;
    }

    protected Shape(XMLStreamReader reader) throws XMLStreamException {
        number = sequence.incrementAndGet();
        log.info(String.format("creating %s with number %d", reader.getLocalName(), number));
        if (reader.next() == START_ELEMENT && reader.next() == CHARACTERS) {
            color = reader.getText();
            if (reader.next() == END_ELEMENT) return;
        }
        throw new XmlParserException(TAGS_CONTENT);
    }

    public abstract double square();

    public String toString() {
        return String.format("%d:%s - %.2f",
                number, color, square());
    }
}
