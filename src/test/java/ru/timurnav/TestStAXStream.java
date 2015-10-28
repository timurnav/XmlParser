package ru.timurnav;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static ru.timurnav.XmlStreamUtil.getValue;

public class TestStAXStream {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new FileInputStream(new File("src\\test\\resources\\shapes1.xml")));
        try {
            while (r.hasNext()) {
                r.next();
                XMLEventReader eventReader = factory.createXMLEventReader(r);
                XMLEvent e = eventReader.nextEvent();
                if (e.isCharacters() && ((Characters) e).isWhiteSpace())
                    continue;
                System.out.println(e);
            }
        } finally {
            r.close();
        }
    }
}
