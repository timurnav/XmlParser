package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;

public class XmlParser implements Runnable {

    public int counter = -1;

    @Override
    public void run() {
        try {
            while (true) {
                if (counter != -1) {
                    if (counter == 0) break;
                    counter--;
                }
                Shape shape = ParserMain.SHAPE_QUEUE.take();
                System.out.println(shape);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
