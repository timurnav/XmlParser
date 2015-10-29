package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;
import ru.timurnav.model.ShapeFactory;

import static ru.timurnav.model.ShapeType.getShapeTypeByXmlString;

public class XmlParser implements Runnable {

    public int counter = -1;

    @Override
    public void run() {
        try {
            while (true) {
                if (counter != -1){
                    if (counter == 0) break;
                    counter--;
                }
                String xmlString = ParserMain.XML_STRING_QUEUE.take();
                Shape shape = ShapeFactory.getShape(getShapeTypeByXmlString(xmlString), xmlString);
                System.out.println(shape);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
