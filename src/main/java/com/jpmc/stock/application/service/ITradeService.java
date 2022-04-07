package com.jpmc.stock.application.service;

import com.jpmc.stock.application.model.SimpleStock;
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
    boolean record(Trade trade);

    /**
     * Return all trades by given Stock
     *
     * @param stock
     * @return
     */
    List<Trade> findAllTradesByStock(SimpleStock stock);
}
