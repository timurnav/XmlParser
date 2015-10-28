package ru.timurnav;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.timurnav.model.Shape;
import ru.timurnav.model.shapes.Circle;
import ru.timurnav.model.shapes.Rectangle;
import ru.timurnav.model.shapes.Square;
import ru.timurnav.model.shapes.Triangle;
import ru.timurnav.xmlReader.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestMain {

    private static final String EXPECTED_CONSOLE_OUTPUT_MAIN =
            "1:red - 6,51\r\n" +
                    "2:green - 321,94\r\n" +
                    "3:white - 2,58\r\n" +
                    "4:blue - 2,25\r\n";

    private static final Queue<String> EXPECTED_STRING_QUEUE = new ConcurrentLinkedQueue<>(
            Arrays.asList("<triangle><color>red</color><side>3.1</side><side>4.2</side><side>5.3</side></triangle>",
                    "<circle><color>green</color><diameter>10.123</diameter></circle>",
                    "<rectangle><color>white</color><side>1.45</side><side>1.78</side></rectangle>",
                    "<square><color>blue</color><side>1.5</side></square>")
    );

    private static final Queue<Shape> EXPECTED_SHAPE_QUEUE = new ConcurrentLinkedQueue<>(
            Arrays.asList(
                    new Triangle("<triangle><color>red</color><side>3.1</side><side>4.2</side><side>5.3</side></triangle>", 1),
                    new Circle("<circle><color>green</color><diameter>10.123</diameter></circle>", 2),
                    new Rectangle("<rectangle><color>white</color><side>1.45</side><side>1.78</side></rectangle>", 3),
                    new Square("<square><color>blue</color><side>1.5</side></square>", 4)
    ));

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSplitter() {
        Assert.assertTrue(ParserMain.XML_STRING_QUEUE.isEmpty());
        new XmlSplitter(new File("src\\test\\resources\\shapes1.xml")).run();
        while (EXPECTED_STRING_QUEUE.size() > 0) {
            Assert.assertEquals(EXPECTED_STRING_QUEUE.poll(), ParserMain.XML_STRING_QUEUE.poll());
        }
    }

    @Test
    public void testSplitterFail() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionUtils.ExceptionType.OPEN_TAG.getMessage() + ExceptionUtils.getAdditionalMessage());
        new XmlSplitter(new File("src\\test\\resources\\shapes_open_tag.xml")).run();
    }

    @Test
    public void testParser() {
        Assert.assertTrue(ParserMain.SHAPES_QUEUE.isEmpty());
        while (EXPECTED_STRING_QUEUE.size() > 0) {
            ParserMain.XML_STRING_QUEUE.offer(EXPECTED_STRING_QUEUE.poll());
        }
        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();
        new XmlParser(thread).run();
        while (EXPECTED_SHAPE_QUEUE.size() > 0) {
            Assert.assertEquals(EXPECTED_SHAPE_QUEUE.poll(), ParserMain.SHAPES_QUEUE.poll());
        }
    }

    @Test
    public void testPrinter() {
        Assert.assertTrue(ParserMain.SHAPES_QUEUE.isEmpty());
        while (EXPECTED_SHAPE_QUEUE.size() > 0) {
            ParserMain.SHAPES_QUEUE.offer(EXPECTED_SHAPE_QUEUE.poll());
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream consoleStream = System.out;
        System.setOut(new PrintStream(outputStream));
        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();
        new Printer(thread, thread).run();
        System.setOut(consoleStream);
        Assert.assertEquals(outputStream.toString(), EXPECTED_CONSOLE_OUTPUT_MAIN);
    }
}
