package com.jpmc.stock.application.service;

import com.jpmc.stock.application.dao.ISimpleStockRepository;
import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.model.TradeIndicator;
import com.jpmc.stock.application.service.ITradeService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestTradeService extends AbstractSpringTest{

    @Autowired
    private ITradeService tradeService;

    @Autowired
    ISimpleStockRepository stockRepository;

    @Test
    public void testRecordTrade() throws SimpleStockNotFoundException {
        final SimpleStock stock = stockRepository.findStockBySymbol("ALE");
        Trade trade = new Trade(TradeIndicator.BUY, stock);
        final boolean record = tradeService.record(trade);
        Assert.assertTrue(record);
        final List<Trade> tradesByStock = tradeService.findAllTradesByStock(stock);
        Assert.assertNotNull(tradesByStock);
        Assert.assertTrue(tradesByStock.size() ==1);
    }
}
