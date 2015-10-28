package ru.timurnav;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestStAXEvent {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = inputFactory.createXMLEventReader(new FileInputStream(new File("src\\test\\resources\\shapes1.xml")));
        try {
            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();
                if (e.isCharacters() && ((Characters) e).isWhiteSpace())
                    continue;

                System.out.println(e);
            }
        } finally {
            reader.close();
        }
    }

}
