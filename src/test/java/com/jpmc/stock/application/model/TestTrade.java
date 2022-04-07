package com.jpmc.stock.application.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.SimpleStockType;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.model.TradeIndicator;

import java.util.Date;

/**
 * Test the Trade object
 */
public class TestTrade {

    private Trade testObject;

    @Before
    public void setUp() {
        testObject = new Trade(TradeIndicator.BUY, new SimpleStock(SimpleStockType.COMMON_STOCK, SimpleStockSymbol.ALE.name()));
    }

    @Test
    public void testTradeCreation(){
        Assert.assertNotNull(testObject);
        Trade another = new Trade(TradeIndicator.BUY, new SimpleStock(SimpleStockType.COMMON_STOCK, SimpleStockSymbol.ALE.name()));
        Assert.assertTrue(testObject.equals(another));
        Assert.assertFalse(testObject.equals(new Trade(TradeIndicator.SELL, new SimpleStock(SimpleStockType.PREFERRED_STOCK, SimpleStockSymbol.JOE.name()))));
    }

    @Test
    public void testMutation(){
        testObject.setTime(new Date());
        testObject.setShareQuantity(new Double(5.0));

        Assert.assertTrue(TradeIndicator.BUY.equals(testObject.getIndicator()));
        Assert.assertEquals(new SimpleStock(SimpleStockType.COMMON_STOCK, SimpleStockSymbol.ALE.name()), testObject.getSimpleStock());
        Assert.assertTrue(Double.compare(new Double(5.0), testObject.getShareQuantity())== 0);
        Assert.assertTrue(Double.compare(new Double(0.0), testObject.getPrice())== 0);
    }
}
