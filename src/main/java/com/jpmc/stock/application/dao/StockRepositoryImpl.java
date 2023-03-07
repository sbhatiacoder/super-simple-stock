package com.jpmc.stock.application.dao;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.SimpleStockType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation of Stock Repository
 */
@Repository
public class StockRepositoryImpl implements ISimpleStockRepository {

    private static final Map<SimpleStockSymbol, SimpleStock> repo = new HashMap<>();

    static {
        repo.put(SimpleStockSymbol.TEA, new SimpleStock(SimpleStockSymbol.TEA, SimpleStockType.COMMON_STOCK, 0.0, 0.0, 100.0));
        repo.put(SimpleStockSymbol.POP, new SimpleStock(SimpleStockSymbol.POP, SimpleStockType.COMMON_STOCK, 8.0, 0.0, 100.0));
        repo.put(SimpleStockSymbol.ALE, new SimpleStock(SimpleStockSymbol.ALE, SimpleStockType.COMMON_STOCK, 23.0, 0.0, 60.0));
        repo.put(SimpleStockSymbol.GIN, new SimpleStock(SimpleStockSymbol.GIN, SimpleStockType.PREFERRED_STOCK, 8.0, 0.2, 100.0));
        repo.put(SimpleStockSymbol.JOE, new SimpleStock(SimpleStockSymbol.JOE, SimpleStockType.COMMON_STOCK, 13.0, 0.0, 250.0));
    }
    @Override
    public SimpleStock findStockBySymbol(SimpleStockSymbol symbol) throws SimpleStockNotFoundException {
        return repo.get(symbol);
    }
    public Map<SimpleStockSymbol, SimpleStock> getRepo() {
        return repo;
    }
}
