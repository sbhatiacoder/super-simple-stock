package com.jpmc.stock.application.exception;

/**
 * Exception thrown specifically by the Stock Service during execution of methods within the stock service application
 */
public class SimpleStockServiceException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SimpleStockServiceException(String message) {
        super(message);
    }

}
