package com.workq.tracelisteners.messaging;

/**
 * This exception is thrown when a {@link MessagePublisher} fails to publish a message
 */
@SuppressWarnings("unused")
public class PublishingFailedException extends RuntimeException {

    public PublishingFailedException() {
    }

    public PublishingFailedException(String message) {
        super(message);
    }

    public PublishingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PublishingFailedException(Throwable cause) {
        super(cause);
    }

    public PublishingFailedException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
