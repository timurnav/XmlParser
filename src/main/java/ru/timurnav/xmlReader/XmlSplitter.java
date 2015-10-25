package ru.timurnav.xmlReader;

import ru.timurnav.Main;
import ru.timurnav.model.ShapeType;

import java.io.*;

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
            //check first tag - it should be the open tag <shape>
            if (!ROOT_SHAPE.matchOpenTag(reader.readLine())) {
                throw new IllegalArgumentException();
            }
            String currentTag = reader.readLine();
            do {
                //get all info about shape by open tag
                //also it checks is the tag correct and protect by infinite loop
                ShapeType currentShapeType = getShapeTypeByOpenTag(currentTag);
                StringBuilder sb = new StringBuilder(currentTag);
                //iterate lines while line don't match the close tag of the current shape
                while (currentShapeType.mismatchCloseTag(currentTag)){
                    currentTag = reader.readLine();
                    if (currentTag == null || currentTag.isEmpty()) {
                        throw new IllegalArgumentException("Can't find close tag " + currentShapeType.getCloseTag());
                    }
                    sb.append(currentTag);
                }
                //here we add new Xml String to the global queue and iterate farther..
                Main.XML_STRING_QUEUE.offer(sb.toString());
                currentTag = reader.readLine();
                //and we'll repeat it while the current tag doesn't match the last tag of root element
            } while (ROOT_SHAPE.mismatchCloseTag(currentTag));
            //and when we reach it the method will be done!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CustomTrimReader extends BufferedReader {

        public CustomTrimReader(Reader in) {
            super(in);
        }

        @Override
        public String readLine() throws IOException {
            return super.readLine().trim();
        }
    }
}
