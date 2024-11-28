package net.proselyte.trpo.exceptions;


public class NoSuchException extends RuntimeException{
    public NoSuchException(String message) {
        super(message);
    }
}