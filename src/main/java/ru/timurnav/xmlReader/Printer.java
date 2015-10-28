package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;

public class Printer implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Shape polledShape = ParserMain.SHAPES_QUEUE.take();
                System.out.println(polledShape);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
