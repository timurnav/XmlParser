package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;
import ru.timurnav.model.ShapeFactory;
import ru.timurnav.model.ShapeType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static ru.timurnav.model.ShapeType.CIRCLE;
import static ru.timurnav.model.ShapeType.getShapeTypeByXmlString;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;

public class XmlParser implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                String xmlString = ParserMain.XML_STRING_QUEUE.take();
                Shape shape = ShapeFactory.getShape(getShapeTypeByXmlString(xmlString), xmlString);
                System.out.println(shape);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
