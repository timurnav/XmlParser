package ru.timurnav;

public class TestMain {
//
//    private static Queue<Shape> EXPECTED_SHAPE_QUEUE;
//
//    private static final String EXPECTED_CONSOLE_OUTPUT_MAIN =
//            "1:red - 6,51\r\n" +
//                    "2:green - 321,94\r\n" +
//                    "3:white - 2,58\r\n" +
//                    "4:blue - 2,25\r\n";
//
//    @Before
//    public void setUp() throws Exception {
//        EXPECTED_SHAPE_QUEUE = new ConcurrentLinkedQueue<>(
//                Arrays.asList(
//                        new Triangle(1, "red", 3.1f, 4.2f, 5.3f),
//                        new Circle(2, "green", 10.123f),
//                        new Rectangle(3, "white", 1.45f, 1.78f),
//                        new Square(4, "blue", 1.5f))
//        );
//        while (Processing.SHAPE_QUEUE.size() > 0) {
//            Processing.SHAPE_QUEUE.clear();
//        }
//    }
//
//    @Test
//    public void testSplitter() {
//        Assert.assertTrue(Processing.SHAPE_QUEUE.isEmpty());
//        new Parsing(new File("src/test/resources/shapes1.xml"), exchanger).run();
//        while (EXPECTED_SHAPE_QUEUE.size() > 0) {
//            Assert.assertEquals(EXPECTED_SHAPE_QUEUE.poll(), Processing.SHAPE_QUEUE.poll());
//        }
//    }
//
//    @Test(expected = XmlParserException.class)
//    public void testSplitterFail() {
//        new Parsing(new File("src/test/resources/shapes_open_tag.xml"), exchanger).run();
//    }
//
//    @Test
//    public void testParser() throws InterruptedException {
//        while (EXPECTED_SHAPE_QUEUE.size() > 0) {
//            Processing.SHAPE_QUEUE.offer(EXPECTED_SHAPE_QUEUE.poll());
//        }
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintStream consoleStream = System.out;
//        System.setOut(new PrintStream(outputStream));
//        Printing printer = new Printing();
//        printer.counter = 4;
//        printer.run();
//        System.setOut(consoleStream);
//        Assert.assertEquals(EXPECTED_CONSOLE_OUTPUT_MAIN, outputStream.toString());
//    }
}
