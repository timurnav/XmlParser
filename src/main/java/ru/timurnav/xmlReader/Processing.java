package ru.timurnav.xmlReader;


import ru.timurnav.model.Shape;
import ru.timurnav.util.XmlParserException;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.LogManager;

import static ru.timurnav.util.ExceptionUtils.ExceptionType.ARGUMENTS;
import static ru.timurnav.util.ExceptionUtils.ExceptionType.XML_FILE;

public class Processing {

    public static final BlockingQueue<Shape> SHAPE_QUEUE = new LinkedBlockingQueue<>();
    public static volatile AtomicInteger counter = new AtomicInteger(0);
    public static volatile boolean shotdown = false;

    public static void mainParserClass(String[] args) {
        if (args.length == 0) {
            throw new XmlParserException(ARGUMENTS);
        }
        String fileName = args[0];
        if (!fileName.endsWith(".xml")) {
            throw new XmlParserException(XML_FILE);
        }
        File xmlFile = new File(fileName);
        if (!xmlFile.isFile() || !xmlFile.canRead()) {
            throw new XmlParserException(ARGUMENTS);
        }
        try(FileInputStream fis = new FileInputStream("src/main/java/ru/timurnav/logging.properties")) {
            LogManager.getLogManager().readConfiguration(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new Thread(new Parsing(xmlFile)).start();
            new Thread(new Printing()).start();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
