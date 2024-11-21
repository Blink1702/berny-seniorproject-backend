package com.bernardo.me.seniorproject_backend.services;

public class DuplicateException extends Exception {
    public DuplicateException() {
        super("Attempt to insert duplicate element.");
    }
}
