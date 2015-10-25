package ru.timurnav;


import ru.timurnav.model.Shape;
import ru.timurnav.xmlReader.Printer;
import ru.timurnav.xmlReader.XmlParser;
import ru.timurnav.xmlReader.XmlSplitter;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static final Queue<String> XML_STRING_QUEUE = new ConcurrentLinkedQueue<>();
    public static final Queue<Shape> SHAPES_QUEUE = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "At least one argument should be transmitted!");
        }
        String fileName = args[0];
        if (!fileName.endsWith(".xml"))
            throw new IllegalArgumentException(
                    "Please specify an *.xml file as first argument!");
        File xmlFile = new File(fileName);
        if (!xmlFile.isFile() || !xmlFile.canRead()) {
            throw new IllegalArgumentException("Can not read file");
        }

        Thread splitterThread = new Thread(new XmlSplitter(xmlFile));
        splitterThread.start();
        Thread parserThread = new Thread(new XmlParser(splitterThread));
        parserThread.start();
        Thread printerThread = new Thread((new Printer(splitterThread, parserThread)));
        printerThread.start();
    }
}
