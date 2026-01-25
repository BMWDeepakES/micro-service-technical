package com.rating.exception.handler;

public class RatingNotFound extends RuntimeException {
    public RatingNotFound(String message) {
        super(message);
    }
}
