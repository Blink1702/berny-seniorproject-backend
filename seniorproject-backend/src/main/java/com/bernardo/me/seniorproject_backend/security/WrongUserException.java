package com.bernardo.me.seniorproject_backend.security;

public class WrongUserException extends Exception {
    public WrongUserException() {
        super("User is not allowed to perform this action");
    }
}