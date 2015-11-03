package ru.timurnav;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.timurnav.model.Shape;
import ru.timurnav.model.shapes.Circle;
import ru.timurnav.model.shapes.Rectangle;
import ru.timurnav.model.shapes.Square;
import ru.timurnav.model.shapes.Triangle;
import ru.timurnav.xmlReader.ExceptionUtils;
import ru.timurnav.xmlReader.ParserMain;
import ru.timurnav.xmlReader.Printer;
import ru.timurnav.xmlReader.XmlParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestMain {

    private static Queue<Shape> EXPECTED_SHAPE_QUEUE;

    private static final String EXPECTED_CONSOLE_OUTPUT_MAIN =
            "1:red - 6,51\r\n" +
                    "2:green - 321,94\r\n" +
                    "3:white - 2,58\r\n" +
                    "4:blue - 2,25\r\n";

    @Before
    public void setUp() throws Exception {
        EXPECTED_SHAPE_QUEUE = new ConcurrentLinkedQueue<>(
                Arrays.asList(
                        new Triangle(1, "red", 3.1f, 4.2f, 5.3f),
                        new Circle(2, "green", 10.123f),
                        new Rectangle(3, "white", 1.45f, 1.78f),
                        new Square(4, "blue", 1.5f))
        );
        while (ParserMain.SHAPE_QUEUE.size() > 0) {
            ParserMain.SHAPE_QUEUE.clear();
        }
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSplitter() {
        Assert.assertTrue(ParserMain.SHAPE_QUEUE.isEmpty());
        new XmlParser(new File("src/test/resources/shapes1.xml")).run();
        while (EXPECTED_SHAPE_QUEUE.size() > 0) {
            Assert.assertEquals(EXPECTED_SHAPE_QUEUE.poll(), ParserMain.SHAPE_QUEUE.poll());
        }
    }

    @Test
    public void testSplitterFail() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionUtils.ExceptionType.TAG.getMessage() + ExceptionUtils.getAdditionalMessage());
        new XmlParser(new File("src/test/resources/shapes_open_tag.xml")).run();
    }

    @Test
    public void testParser() throws InterruptedException {
        while (EXPECTED_SHAPE_QUEUE.size() > 0) {
            ParserMain.SHAPE_QUEUE.offer(EXPECTED_SHAPE_QUEUE.poll());
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream consoleStream = System.out;
        System.setOut(new PrintStream(outputStream));
        Printer printer = new Printer();
        printer.counter = 4;
        printer.run();
        System.setOut(consoleStream);
        Assert.assertEquals(EXPECTED_CONSOLE_OUTPUT_MAIN, outputStream.toString());
    }
}
