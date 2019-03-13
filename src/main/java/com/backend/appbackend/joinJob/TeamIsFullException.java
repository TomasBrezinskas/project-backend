package com.backend.appbackend.joinJob;

public class TeamIsFullException extends Exception {
    public TeamIsFullException(String message) {
        super(message);
    }
}
