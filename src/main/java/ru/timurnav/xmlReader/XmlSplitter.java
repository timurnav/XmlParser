package ru.timurnav.xmlReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XmlSplitter implements Runnable{

    private final File xmlFile;

    public XmlSplitter(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public void run() {
        parseXmlFile();
    }

    public void parseXmlFile() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader tempReader = factory.createXMLEventReader(new FileInputStream(xmlFile));
                    XMLEventReader reader = factory.createFilteredReader(
                            tempReader, event -> event.getEventType() != 4);
            try {
                while (reader.hasNext()){
                    XMLEvent event = reader.peek();
//                    System.out.println(event.);
//                    System.out.println("event: " + event);
//                    System.out.println("type: " + event.getEventType());
                    reader.nextEvent();
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw ExceptionUtils.getExpetionWithMessage(ExceptionUtils.ExceptionType.XML_FILE);
        }


       /* try (CustomTrimReader reader = new CustomTrimReader(
                new InputStreamReader(new FileInputStream(xmlFile)))) {

            if (!ROOT_SHAPE.matchOpenTag(reader.readLine())) {
                throw ExceptionUtils.getExpetionWithMessage(OPEN_ROOT_TAG);
            }
            String currentTag = reader.readAndCheckTag(OPEN_TAG);

            do {

                ShapeType currentShapeType = getShapeTypeByOpenTag(currentTag);
                StringBuilder sb = new StringBuilder(currentTag);

                while (currentShapeType.mismatchCloseTag(currentTag)){
                    currentTag = reader.readAndCheckTag(CLOSE_TAG);
                    sb.append(currentTag);
                }

                ParserMain.XML_STRING_QUEUE.offer(sb.toString());
                currentTag = reader.readAndCheckTag(CLOSE_ROOT_TAG);

            } while (ROOT_SHAPE.mismatchCloseTag(currentTag));

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
