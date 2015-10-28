package ru.timurnav;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestStAX {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File("src\\test\\resources\\shapes1.xml")));
        try {
            while (true) {

                switch (reader.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:

                        System.out.println("Start element: " + reader.getName());
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        System.out.println("End element: " + reader.getName());
                        break;
                }
                if (!reader.hasNext())
                    break;
                reader.next();
            }
        } finally {
            reader.close();
        }
    }

}
