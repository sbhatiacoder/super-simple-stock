package com.jpmc.stock.application.exception;

/**
 * Exception to mention the stock not found
 */
public class SimpleStockNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SimpleStockNotFoundException(String message) {
        super(message);
    }
}
