package ru.timurnav.xmlReader;

import ru.timurnav.model.Shape;

import java.util.concurrent.TimeUnit;

public class Printing implements Runnable {

    @Override
    public void run() {
        try {
            while (!Processing.shotdown || Processing.counter.get() > 0) {
                Shape shape = Processing.SHAPE_QUEUE.poll(1, TimeUnit.SECONDS);
                if (shape == null) break;
                System.out.println(shape);
                Processing.counter.decrementAndGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
