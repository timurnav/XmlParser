package ru.timurnav;

import ru.timurnav.xmlReader.Processing;

public class PseudoMain {
    public static void main(String[] args) {
//        Main.main(new String[0]);
//        Main.main(new String[]{""});
//        Processing.mainParserClass(new String[]{"src/test/resources/shapes_tag_content.xml"});
//        Processing.mainParserClass(new String[]{"src/test/resources/shapes_open_tag.xml"});
        Processing.mainParserClass(new String[]{"src/test/resources/1000shapes.xml"});
    }
}
