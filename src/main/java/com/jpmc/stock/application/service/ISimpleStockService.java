package com.jpmc.stock.application.service;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.model.SimpleStockSymbol;

import java.util.Map;

/**
 * Simple Stock Service
 */
public interface ISimpleStockService {

    /**
     * Calculate the dividend yield for the given Stock and price
     */
    Double calculateDividendYield(SimpleStockSymbol stockSymbol, Double price) throws SimpleStockServiceException, SimpleStockNotFoundException;

    /**
     * Calculates the PE ration for the given Stock and price
     */
    Double calculatePERatio(SimpleStockSymbol stockSymbol, Double price) throws SimpleStockServiceException, SimpleStockNotFoundException;


    /**
     * Calculate the Stock price for the given Stock from the trades recorded in given minutes
     */
    Double calculateVolumeWeightedStockPrice(SimpleStockSymbol stockSymbol, int minutes) throws SimpleStockNotFoundException;

    /**
     * Calculates the All Share Index using the geometric mean of prices for all stocks
     */
    Map<String, Double> calculateAllShareIndex() throws SimpleStockNotFoundException;

}
