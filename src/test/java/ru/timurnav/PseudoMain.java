package ru.timurnav;

import ru.timurnav.xmlReader.ParserMain;

public class PseudoMain {
    public static void main(String[] args) {
//        Main.main(new String[0]);
//        Main.main(new String[]{""});
//        ParserMain.mainParserClass(new String[]{"src/test/resources/shapes_tag_content.xml"});
//        ParserMain.mainParserClass(new String[]{"src/test/resources/shapes_open_tag.xml"});
        ParserMain.mainParserClass(new String[]{"src/test/resources/1000shapes.xml"});
    }
}
