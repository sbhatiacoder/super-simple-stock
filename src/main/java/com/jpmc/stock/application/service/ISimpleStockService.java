package com.jpmc.stock.application.service;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.model.SimpleStock;

import java.util.List;

/**
 * Simple Stock Service
 */
public interface ISimpleStockService {


    /**
     * Calculate the dividend yield for the given Stock and price
     *
     * @param stockSymbol
     * @param price
     * @return
     * @throws SimpleStockServiceException
     */
    Double calculateDividendYield(String stockSymbol, double price) throws SimpleStockServiceException, SimpleStockNotFoundException;

    /**
     * Calculates the PE ration for the given Stock and price
     *
     * @param stockSymbol
     * @param price
     * @return
     * @throws SimpleStockServiceException
     */
    Double calculatePERatio(String stockSymbol, double price) throws SimpleStockServiceException, SimpleStockNotFoundException;


    /**
     * Calculate the Stock price for the given Stock from the trades recorded in given minutes
     *
     * @param symbol
     * @return
     * @throws SimpleStockNotFoundException
     */
    double calculateVolumeWeightedStockPrice(String symbol) throws SimpleStockNotFoundException;

    /**
     * Calculates the All Share Index using the geometric mean of prices for all stocks
     * @param stocks
     * @return
     * @throws SimpleStockServiceException
     */
    double calculateAllShareIndex(List<SimpleStock> stocks);
}
