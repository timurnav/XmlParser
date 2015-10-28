package ru.timurnav;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.io.StringReader;

/**
 * User: gkislin
 * Date: 10/31/11
 */
public class XmlStreamUtil {

    private static class LazyInitFactory {
        private static final XMLInputFactory XML_FACTORY = XMLInputFactory.newInstance();
    }

    public static XMLStreamReader createReader(String str) throws XMLStreamException {
        return LazyInitFactory.XML_FACTORY.createXMLStreamReader(new StringReader(str));
    }

    public static XMLStreamReader createReader(InputStream is, String encoding) throws XMLStreamException {
        return LazyInitFactory.XML_FACTORY.createXMLStreamReader(is, encoding);
    }

    public static boolean doUntil(XMLStreamReader reader, int stopEvent, String value, int repeat) throws XMLStreamException {
        for (int i = 0; i < repeat; i++) {
            if (!doUntil(reader, stopEvent, value)) {
                return false;
            }
        }
        return true;
    }

    public static String doUntilAny(XMLStreamReader reader, int stopEvent, String... values) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent) {
                String xmlValue = getValue(event, reader);
                for (String value : values) {
                    if (value.equals(xmlValue)) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

    public static boolean doUntil(XMLStreamReader reader, int stopEvent, String value) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent) {
                if (value.equals(getValue(event, reader))) {
                    return true;
                }
            }
        }
        return false;
    }


    public static String getElementValue(XMLStreamReader reader, String element) throws XMLStreamException {
        XmlStreamUtil.doUntil(reader, XMLEvent.START_ELEMENT, element);
        reader.next();
        return reader.getText();
    }

    public static String getNextValue(int stopEvent, XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == stopEvent) {
                return getValue(event, reader);
            }
        }
        return null;
    }

    public static String getValue(int event, XMLStreamReader reader) throws XMLStreamException {
        return (event == XMLEvent.CHARACTERS) ? reader.getText() : reader.getLocalName();
    }

    public static void close(XMLStreamReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                // empty
            }
        }
    }

}
