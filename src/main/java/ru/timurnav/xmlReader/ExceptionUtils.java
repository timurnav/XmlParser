package ru.timurnav.xmlReader;

public class ExceptionUtils {

    public static IllegalArgumentException getExpetionWithMessage(ExceptionType exceptionType) throws RuntimeException {
        return new IllegalArgumentException(exceptionType.message + additionalMessage);
    }

    private static String additionalMessage = " Please specify a correct *.xml file as the first argument!";

    public static String getAdditionalMessage() {
        return additionalMessage;
    }

    public enum ExceptionType{
        TAGS_CONTENT("Can't parse xml. The File don't correspond to the schema. To get the reference of " +
                "the schema launch app without any parameters.\n" +
                "This case probably one of shapes contains incorrect content."),
        TAG("Can't split xml. The File don't correspond to the schema. To get the address of " +
                "the schema launch app without any parameters. \nThis case probably there is some syntax mistake:" +
                "open or close tags of one of the shapes is incorrect or missing."),
        ARGUMENTS("Hi! You've tried to launch this application, but it was aborted.\n" +
                "The application can process xml-files which corresponds " +
                "to the scheme https://github.com/timurnav/XmlParser/blob/master/src/main/resources/shapes.xsd\n" +
                "You should transmit an absolute path to some *.xml file.\n" +
                "To launch the application at least one argument should be transmitted."),
        XML_FILE("Transmitted file isn't an *.xml file or isn't able to be read.")
        ;

        private String message;

        ExceptionType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
