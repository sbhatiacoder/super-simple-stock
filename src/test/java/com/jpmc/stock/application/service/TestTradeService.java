package com.jpmc.stock.application.service;

import com.jpmc.stock.application.dao.ISimpleStockRepository;
import com.jpmc.stock.application.dao.StockRepositoryImpl;
import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.model.TradeIndicator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class TestTradeService {

    private final ITradeService tradeService = new TradeServiceImpl();

    ISimpleStockRepository stockRepository = new StockRepositoryImpl();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(tradeService, "stockRepository", stockRepository);

    }

    @Test
    public void testRecordTrade() throws SimpleStockNotFoundException {
        Trade trade = new Trade(SimpleStockSymbol.GIN, TradeIndicator.BUY, 20.0, 5);
        final boolean record = tradeService.record(trade);
        assertTrue(record);
        final List<Trade> tradesByStock = tradeService.findAllTradesByStockSymbol(SimpleStockSymbol.GIN);
        assertNotNull(tradesByStock);
        assertEquals(1, tradesByStock.size());
    }
}
