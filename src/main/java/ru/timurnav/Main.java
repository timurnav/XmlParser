package ru.timurnav;

import ru.timurnav.xmlReader.ParserMain;

public class Main {

    public static void main(String[] args) {
        try {
            ParserMain.mainParserClass(args);
        } catch (Exception e) {
            System.out.println("Unexpected configuration. The application had aborted " +
                    "before it started to process with following message:");
            System.out.println(e.getMessage());
        }
    }
}
