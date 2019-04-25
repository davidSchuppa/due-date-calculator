package com.davidschuppa;

public class SubmissionTimeException extends Exception {

    public SubmissionTimeException() {
    }

    public SubmissionTimeException(String message) {
        super(message);
    }

    public SubmissionTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubmissionTimeException(Throwable cause) {
        super(cause);
    }

    public SubmissionTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
