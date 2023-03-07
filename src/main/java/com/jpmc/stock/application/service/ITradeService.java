package com.jpmc.stock.application.service;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.Trade;

import java.util.List;

/**
 * Trade service provides functions to record {@link com.jpmc.stock.application.model.Trade}
 */
public interface ITradeService {

    /**
     * Records a trade for the given {@link com.jpmc.stock.application.model.Trade}
     *
     * @param trade
     * @return
     */
    boolean record(Trade trade) throws SimpleStockNotFoundException;

    /**
     * Return all trades by given Stock
     *
     * @param stock
     * @return
     */
    List<Trade> findAllTradesByStockSymbol(SimpleStockSymbol stock) throws SimpleStockNotFoundException;
}
