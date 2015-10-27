package ru.timurnav.reader;

public class ExceptionUtils {

    public static IllegalArgumentException getExpetionWithMessage(ExceptionType exceptionType) throws RuntimeException {
        return new IllegalArgumentException(exceptionType.message + additionalMessage);
    }

    private static String additionalMessage = " Please specify a correct *.xml file as the first argument!";

    public static String getAdditionalMessage() {
        return additionalMessage;
    }

    public enum ExceptionType{
        TAGS_CONTENT("Can't parse xml. Tag contains incorrect content."),
        OPEN_TAG("Can't split xml. Incorrect open tag."),
        CLOSE_TAG("Can't split xml. Can't find close tag."),
        OPEN_ROOT_TAG("Can't split xml. Incorrect root open tag."),
        CLOSE_ROOT_TAG("Can't split xml. Root close tag is missed."),
        ARGUMENTS("At least one argument should be transmitted."),
        XML_FILE("Transmitted file isn't an *.xml file.")
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
