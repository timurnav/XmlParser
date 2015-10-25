package ru.timurnav;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class TestMain {

    private static final String CONSOLE_OUTPUT_MAIN =
            "1:red - 6,51\r\n" +
            "2:green - 321,94\r\n" +
            "3:white - 2,58\r\n" +
            "4:blue - 2,25\r\n";

    @Test
    public void testMain() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream consoleStream = System.out;
        System.setOut(new PrintStream(outputStream));
        Main.main(new String[]{"src\\test\\resources\\shapes1.xml"});
        TimeUnit.SECONDS.sleep(3);
        System.setOut(consoleStream);
        Assert.assertEquals(outputStream.toString(), CONSOLE_OUTPUT_MAIN);
    }
}
