package com.jpmc.stock.application.dao;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;

import java.util.List;
import java.util.Map;

/**
 * Stock repository
 */
public interface ISimpleStockRepository {

    SimpleStock findStockBySymbol(SimpleStockSymbol symbol) throws SimpleStockNotFoundException;
    Map<SimpleStockSymbol, SimpleStock> getRepo();
}
