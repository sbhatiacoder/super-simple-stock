package com.jpmc.stock.application.service;

import com.jpmc.stock.application.dao.ISimpleStockRepository;
import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple implementation of {@link com.jpmc.stock.application.service.ITradeService}
 */
@Service
public class TradeServiceImpl implements ITradeService {

    @Autowired
    private ISimpleStockRepository stockRepository;

    @Override
    public boolean record(Trade trade) throws SimpleStockNotFoundException {
        SimpleStock stock = stockRepository.findStockBySymbol(trade.getSimpleStockSymbol());

        if (stock != null && stock.getTrades() != null) {
            stock.getTrades().put(trade.getTime(), trade);
            return true;
        }
        return false;
    }

    @Override
    public List<Trade> findAllTradesByStockSymbol(SimpleStockSymbol stockSymbol) throws SimpleStockNotFoundException {
        SimpleStock stock = stockRepository.findStockBySymbol(stockSymbol);
        return stock.getTrades().values().stream().collect(Collectors.toList());
    }

}
