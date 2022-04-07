package com.jpmc.stock.application.dao;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockType;

import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Simple implementation of Stock Repository
 */
@Repository
public class StockRepositoryImpl implements ISimpleStockRepository {

    private static Map<String, SimpleStock> repo = new HashMap<>();

    static {
        repo.put("TEA", new SimpleStock("TEA", SimpleStockType.COMMON_STOCK, 0.0, 0.0, 100.0));
        repo.put("POP", new SimpleStock("POP", SimpleStockType.COMMON_STOCK, 8.0, 0.0, 100.0));
        repo.put("ALE", new SimpleStock("ALE", SimpleStockType.COMMON_STOCK, 23.0, 0.0, 60.0));
        repo.put("GIN", new SimpleStock("GIN", SimpleStockType.PREFERRED_STOCK, 8.0, 0.2, 100.0));
        repo.put("JOE", new SimpleStock("JOE", SimpleStockType.COMMON_STOCK, 13.0, 0.0, 250.0));
    }

    @Override
    public SimpleStock findStockBySymbol(String symbol) throws SimpleStockNotFoundException {
        if (symbol == null || symbol.isEmpty()) {
            throw new SimpleStockNotFoundException("Could not find a stock for the given symbol " + symbol);
        }
        return repo.get(symbol);
    }

    @Override
    public List<SimpleStock> findAll() throws SimpleStockNotFoundException {
        // Return a defensive copy
        return new ArrayList<>(repo.values());
    }
}
