package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;
import ru.timurnav.model.ShapeFactory;
import ru.timurnav.model.ShapeType;
import ru.timurnav.util.XmlParserException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static ru.timurnav.util.ExceptionUtils.ExceptionType.TAG;
import static ru.timurnav.util.ExceptionUtils.ExceptionType.XML_FILE;

public class Parsing implements Runnable {

    private final File xmlFile;

    public Parsing(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public void run() {
        splitXmlFile();
    }

    public void splitXmlFile() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createFilteredReader(
                    factory.createXMLStreamReader(new FileInputStream(xmlFile)),
                    filter -> !filter.isWhiteSpace());
            try {
                while (true) {
                    reader.next();
                    if (reader.isStartElement()) {
                        if (!ShapeType.SHAPES.name().equalsIgnoreCase(reader.getLocalName())) {
                            throw new XmlParserException(TAG);
                        }
                        break;
                    }
                }
                while (reader.hasNext()) {
                    reader.next();
                    if (reader.isStartElement()) {
                        Processing.counter.incrementAndGet();
                        ShapeType shapeType = ShapeType.valueOf(reader.getLocalName().toUpperCase());
                        Shape shape = ShapeFactory.getShape(shapeType, reader);
                        Processing.SHAPE_QUEUE.offer(shape);
                    }
                }
            } finally {
                Processing.shotdown = true;
                reader.close();
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new XmlParserException(XML_FILE);
        }
    }
}
