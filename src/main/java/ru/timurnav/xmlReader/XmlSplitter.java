package ru.timurnav.xmlReader;

import ru.timurnav.ExceptionUtils;
import ru.timurnav.Main;
import ru.timurnav.model.ShapeType;

import java.io.*;

import static ru.timurnav.ExceptionUtils.ExceptionType.CLOSE_TAG;
import static ru.timurnav.ExceptionUtils.ExceptionType.OPEN_TAG;
import static ru.timurnav.ExceptionUtils.ExceptionType.CLOSE_ROOT_TAG;
import static ru.timurnav.ExceptionUtils.ExceptionType.OPEN_ROOT_TAG;
import static ru.timurnav.model.ShapeType.ROOT_SHAPE;
import static ru.timurnav.model.ShapeType.getShapeTypeByOpenTag;

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
        try (CustomTrimReader reader = new CustomTrimReader(
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

                Main.XML_STRING_QUEUE.offer(sb.toString());
                currentTag = reader.readAndCheckTag(CLOSE_ROOT_TAG);

            } while (ROOT_SHAPE.mismatchCloseTag(currentTag));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CustomTrimReader extends BufferedReader {

        public CustomTrimReader(Reader in) {
            super(in);
        }

        public String readAndCheckTag(ExceptionUtils.ExceptionType type) throws IOException {
            while (true) {
                String tmp = super.readLine();
                if (tmp == null) {
                    throw ExceptionUtils.getExpetionWithMessage(type);
                }
                if (!tmp.isEmpty()){
                    return tmp.trim();
                }
            }
        }
    }
}
