package com.jpmc.stock.application.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Trade object
 */
public class TestTrade {

    @Test
    public void testTradeCreation() throws InterruptedException {
        Trade testObject = new Trade(SimpleStockSymbol.ALE, TradeIndicator.BUY, 20.0, 5);
        assertNotNull(testObject);
        Trade another = new Trade(SimpleStockSymbol.ALE, TradeIndicator.BUY, 20.0, 5);
        assertEquals(testObject, another);
        Thread.sleep(1000);
        assertNotEquals(testObject, new Trade(SimpleStockSymbol.ALE, TradeIndicator.BUY, 20.0, 5));
    }
}
