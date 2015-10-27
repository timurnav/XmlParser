package ru.timurnav;

import ru.timurnav.reader.ParserMain;

public class Main {

    public static void main(String[] args) {
        try {
            ParserMain.mainParserClass(args);
        } catch (Exception e) {
            System.out.println("Unexpected configuration. The application aborted with following message");
            System.out.println(e.getMessage());
        }
    }
}
