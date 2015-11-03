package ru.timurnav.util;

public class XmlParserException extends RuntimeException {

    public XmlParserException(ExceptionUtils.ExceptionType type) {
        super(type.getMessage() + ExceptionUtils.additionalMessage);
    }
}
