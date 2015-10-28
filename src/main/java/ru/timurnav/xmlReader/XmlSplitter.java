package ru.timurnav.xmlReader;

import java.io.File;

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

        //TODO implement this
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
