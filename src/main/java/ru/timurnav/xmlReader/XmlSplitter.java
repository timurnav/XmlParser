package ru.timurnav.xmlReader;

import ru.timurnav.model.ShapeType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javax.xml.stream.XMLStreamConstants.*;

public class XmlSplitter implements Runnable {

    private final File xmlFile;

    public XmlSplitter(File xmlFile) {
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
                            throw ExceptionUtils.getExpetionWithMessage(ExceptionUtils.ExceptionType.TAG);
                        }
                        break;
                    }
                }
                while (reader.hasNext()) {
                    reader.next();
                    if (reader.isStartElement()) {
                        ShapeType shapeType = ShapeType.getShapeTypeByOpenTag(reader.getLocalName());
                        readUntilCloseTagOfShape(shapeType, reader);
                    }
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw ExceptionUtils.getExpetionWithMessage(ExceptionUtils.ExceptionType.XML_FILE);
        }
    }

    private void readUntilCloseTagOfShape(ShapeType shapeType, XMLStreamReader reader) throws XMLStreamException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            switch (reader.getEventType()){
                case CHARACTERS:
                    sb.append(reader.getText());
                    break;
                case START_ELEMENT:
                    sb.append(String.format("<%s>", reader.getLocalName()));
                    break;
                case END_ELEMENT:
                    sb.append(String.format("</%s>", reader.getLocalName()));
                    if (shapeType.matchEventName(reader.getLocalName())) {
                        ParserMain.XML_STRING_QUEUE.offer(sb.toString());
                        return;
                    }
                    break;
            }
            reader.next();
        }
    }
}
