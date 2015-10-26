package ru.timurnav.xmlReader;


import ru.timurnav.model.Shape;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.ARGUMENTS;
import static ru.timurnav.xmlReader.ExceptionUtils.ExceptionType.XML_FILE;

public class ParserMain {

    public static final Queue<String> XML_STRING_QUEUE = new ConcurrentLinkedQueue<>();
    public static final Queue<Shape> SHAPES_QUEUE = new ConcurrentLinkedQueue<>();

    public static void mainParserClass(String[] args) {
        if (args.length == 0) {
            throw ExceptionUtils.getExpetionWithMessage(ARGUMENTS);
        }
        String fileName = args[0];
        if (!fileName.endsWith(".xml")){
            throw ExceptionUtils.getExpetionWithMessage(XML_FILE);
        }
        File xmlFile = new File(fileName);
        if (!xmlFile.isFile() || !xmlFile.canRead()) {
            throw new IllegalArgumentException("Can not read file");
        }

        try {
            Thread splitterThread = new Thread(new XmlSplitter(xmlFile));
            splitterThread.start();
            Thread parserThread = new Thread(new XmlParser(splitterThread));
            parserThread.start();
            Thread printerThread = new Thread((new Printer(splitterThread, parserThread)));
            printerThread.start();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}