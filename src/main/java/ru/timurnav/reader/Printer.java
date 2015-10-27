package ru.timurnav.reader;

import ru.timurnav.model.Shape;

public class Printer implements Runnable {

    private final Thread splitter;
    private final Thread parser;

    public Printer(Thread splitter, Thread parser) {

        this.splitter = splitter;
        this.parser = parser;
    }

    @Override
    public void run() {
        while (true) {
            Shape polledShape = ParserMain.SHAPES_QUEUE.poll();
            if (polledShape != null) {
                System.out.println(polledShape);
            } else if (ParserMain.SHAPES_QUEUE.size() == 0 && !splitter.isAlive() && !parser.isAlive()) {
                return;
            }
        }
    }
}
