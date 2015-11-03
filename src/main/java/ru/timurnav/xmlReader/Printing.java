package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;

public class Printing implements Runnable {

    @Override
    public void run() {
        try {
            while (Processing.counter.get() != 0 || !Processing.shotdown) {
                Shape shape = Processing.SHAPE_QUEUE.take();
                System.out.println(shape);
                Processing.counter.decrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
