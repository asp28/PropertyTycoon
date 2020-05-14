package com.mycompany.propertytycoon.exceptions;

/**
 *
 * @author ankeet
 */
public class NotAProperty extends Exception {

    /**
     * constructor
     */
    public NotAProperty() {
        this("not a property exception");
    }
    /**
     * constructor
     * @param message - specific message to be used
     */
    public NotAProperty(String message) {
        super(message);
    }
}
