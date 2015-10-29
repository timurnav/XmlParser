package ru.timurnav.model;

import ru.timurnav.xmlReader.ExceptionUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import java.io.StringReader;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public abstract class Shape {

    protected final static AtomicInteger sequence = new AtomicInteger(0);

    @XmlElement(name = "color")
    protected String color;

    protected int number;

    public Shape() { }

    protected Shape(int number) {
        this.number = number;
    }

    protected static Shape convertXmlToObject(String xmlData, Class<? extends Shape> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Shape shape = (Shape) unmarshaller.unmarshal(new StringReader(xmlData));
            shape.validate();
            return shape;
        } catch (NullPointerException | JAXBException e) {
            throw ExceptionUtils.getExpetionWithMessage(TAGS_CONTENT);
        }
    }

    protected abstract void validate();

    public abstract double square();

    public String toString() {
        return String.format("%d:%s - %.2f",
                number, color, square());
    }
}
