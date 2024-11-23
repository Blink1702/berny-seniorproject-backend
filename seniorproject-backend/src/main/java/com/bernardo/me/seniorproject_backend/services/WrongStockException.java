package com.bernardo.me.seniorproject_backend.services;

public class WrongStockException extends Exception {
    public WrongStockException() {
        super("The stock does not exist");
    }
}