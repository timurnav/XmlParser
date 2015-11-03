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

public class XmlParser implements Runnable {

    private final File xmlFile;

    public XmlParser(File xmlFile) {
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
                        if (!ShapeType.ROOT_SHAPE.matchEventName(reader.getLocalName())) {
                            throw new XmlParserException(TAG);
                        }
                        break;
                    }
                }
                while (reader.hasNext()) {
                    reader.next();
                    if (reader.isStartElement()) {
                        ShapeType shapeType = ShapeType.getShapeTypeByEvent(reader.getLocalName());
                        Shape shape = ShapeFactory.getShape(shapeType, reader);
                        ParserMain.SHAPE_QUEUE.offer(shape);
                    }
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new XmlParserException(XML_FILE);
        }
    }
}
