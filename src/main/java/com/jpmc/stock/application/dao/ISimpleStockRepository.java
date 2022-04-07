package com.jpmc.stock.application.dao;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStock;

import java.util.List;

/**
 * Stock repository
 */
public interface ISimpleStockRepository {

    SimpleStock findStockBySymbol(String symbol) throws SimpleStockNotFoundException;

    List<SimpleStock> findAll() throws SimpleStockNotFoundException;
}
