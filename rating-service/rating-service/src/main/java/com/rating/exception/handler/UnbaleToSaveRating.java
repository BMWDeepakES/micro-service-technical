package com.rating.exception.handler;

public class UnbaleToSaveRating extends RuntimeException {
    public UnbaleToSaveRating(String message) {
        super(message);
    }
}
