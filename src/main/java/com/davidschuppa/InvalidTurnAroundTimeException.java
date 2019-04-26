package com.davidschuppa;

public class InvalidTurnAroundTimeException extends Exception {

    public InvalidTurnAroundTimeException() {
    }

    public InvalidTurnAroundTimeException(String message) {
        super(message);
    }

    public InvalidTurnAroundTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTurnAroundTimeException(Throwable cause) {
        super(cause);
    }

    public InvalidTurnAroundTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
