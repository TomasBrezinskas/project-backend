package com.backend.appbackend.job;

public class TeamIsFullException extends Exception {
    public TeamIsFullException(String message) {
        super(message);
    }
}
