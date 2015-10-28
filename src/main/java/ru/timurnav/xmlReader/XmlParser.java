package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.TAGS_CONTENT;
import static ru.timurnav.model.ShapeType.getClassByXml;

public class XmlParser implements Runnable {

    private Shape convertXmlToObject(String xmlData, Class<? extends Shape> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Shape) unmarshaller.unmarshal(new StringReader(xmlData));
        } catch (JAXBException e) {
            throw ExceptionUtils.getExpetionWithMessage(TAGS_CONTENT);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String xmlString = ParserMain.XML_STRING_QUEUE.take();
                Shape shape = convertXmlToObject(xmlString, getClassByXml(xmlString));
                ParserMain.SHAPES_QUEUE.offer(shape);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
