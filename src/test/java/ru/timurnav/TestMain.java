package ru.timurnav;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.timurnav.xmlReader.ExceptionUtils;
import ru.timurnav.xmlReader.ParserMain;
import ru.timurnav.xmlReader.XmlParser;
import ru.timurnav.xmlReader.XmlSplitter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TestMain {

    private static final String EXPECTED_CONSOLE_OUTPUT_MAIN =
            "1:red - 6,51\n" +
                    "2:green - 321,94\n" +
                    "3:white - 2,58\n" +
                    "4:blue - 2,25\n";

    private static final Queue<String> EXPECTED_STRING_QUEUE = new ConcurrentLinkedQueue<>(
            Arrays.asList("<triangle><color>red</color><side>3.1</side><side>4.2</side><side>5.3</side></triangle>",
                    "<circle><color>green</color><diameter>10.123</diameter></circle>",
                    "<rectangle><color>white</color><side>1.45</side><side>1.78</side></rectangle>",
                    "<square><color>blue</color><side>1.5</side></square>")
    );

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSplitter() {
        Assert.assertTrue(ParserMain.XML_STRING_QUEUE.isEmpty());
        new XmlSplitter(new File("src/test/resources/shapes1.xml")).run();
        while (EXPECTED_STRING_QUEUE.size() > 0) {
            Assert.assertEquals(EXPECTED_STRING_QUEUE.poll(), ParserMain.XML_STRING_QUEUE.poll());
        }
    }

    @Test
    public void testSplitterFail() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ExceptionUtils.ExceptionType.OPEN_TAG.getMessage() + ExceptionUtils.getAdditionalMessage());
        new XmlSplitter(new File("src/test/resources/shapes_open_tag.xml")).run();
    }

    @Test
    public void testParser() throws InterruptedException {
        while (EXPECTED_STRING_QUEUE.size() > 0) {
            ParserMain.XML_STRING_QUEUE.offer(EXPECTED_STRING_QUEUE.poll());
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream consoleStream = System.out;
        System.setOut(new PrintStream(outputStream));
        Thread thread = new Thread(new XmlParser());
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        System.setOut(consoleStream);
        Assert.assertEquals(EXPECTED_CONSOLE_OUTPUT_MAIN, outputStream.toString());
    }
}
