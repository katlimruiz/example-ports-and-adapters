package com.mycompany.core.ports.outbound;

public class ForRepositoryException extends Exception {
    public ForRepositoryException(Exception inner) {
        super(inner);
    }

    public ForRepositoryException(Exception inner, String message) {
        super(message, inner);
    }

    public ForRepositoryException(String message) {
        super(message);
    }
}
