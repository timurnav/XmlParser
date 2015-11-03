package ru.timurnav.xmlReader;


import ru.timurnav.model.Shape;
import ru.timurnav.util.XmlParserException;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static ru.timurnav.util.ExceptionUtils.ExceptionType.ARGUMENTS;
import static ru.timurnav.util.ExceptionUtils.ExceptionType.XML_FILE;

public class ParserMain {

    public static final BlockingQueue<Shape> SHAPE_QUEUE = new LinkedBlockingQueue<>();

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
            throw new IllegalArgumentException("Can not read file");
        }

        try {
            Thread splitterThread = new Thread(new XmlParser(xmlFile));
            splitterThread.start();
/*
            Thread parserThread = new Thread(new Printer());
            parserThread.setDaemon(true);
            parserThread.start();
            splitterThread.join();
            while (true){
                TimeUnit.SECONDS.sleep(1);
                if (SHAPE_QUEUE.size() == 0) break;
            }
*/
            Printer parser = new Printer();
            Thread parserThread = new Thread(parser);
            parserThread.start();
            splitterThread.join();
            parser.counter = SHAPE_QUEUE.size();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
