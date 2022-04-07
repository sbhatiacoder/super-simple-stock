package com.jpmc.stock.application.service;

import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.Trade;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Simple implementation of {@link com.jpmc.stock.application.service.ITradeService}
 */
@Service
public class TradeServiceImpl implements ITradeService {

    private Map<SimpleStock, List<Trade>> trades = new HashMap<>();

    @Override
    public boolean record(Trade trade) {
        List<Trade> list = trades.get(trade.getSimpleStock());
        if (list == null || list.isEmpty()) {
            list = new ArrayList<>();
            trades.put(trade.getSimpleStock(), list);
        }
        return list.add(trade);
    }

    @Override
    public List<Trade> findAllTradesByStock(SimpleStock stock) {
        return Collections.unmodifiableList(trades.get(stock));
    }

}
