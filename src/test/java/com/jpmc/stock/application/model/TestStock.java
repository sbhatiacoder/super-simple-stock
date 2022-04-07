package com.jpmc.stock.application.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.SimpleStockType;

/**
 * Test the Stock
 */
public class TestStock {

    private SimpleStock jpMorgan;

    @Before
    public void setUp() {
        jpMorgan = new SimpleStock(SimpleStockType.COMMON_STOCK, SimpleStockSymbol.ALE.name());
    }

    @Test
    public void testStock(){
        Assert.assertNotNull(jpMorgan);
        Assert.assertTrue(jpMorgan.equals(new SimpleStock(SimpleStockType.COMMON_STOCK, SimpleStockSymbol.ALE.name())));

        SimpleStock gin = new SimpleStock(SimpleStockType.COMMON_STOCK, SimpleStockSymbol.GIN.name());
        Assert.assertFalse(jpMorgan.equals(gin));

    }

    @Test
    public void testMutation(){
        jpMorgan.setFixedDividend(new Double(12.0));
        jpMorgan.setLastDividend(new Double(5.0));
        jpMorgan.setParValue(new Double(10.0));
        jpMorgan.setTickerPrice(new Double(20.0));

        Assert.assertTrue(Double.compare(new Double(12.0), jpMorgan.getFixedDividend())== 0);
        Assert.assertTrue(Double.compare(new Double(5.0), jpMorgan.getLastDividend())== 0);
        Assert.assertTrue(Double.compare(new Double(10.0), jpMorgan.getParValue())== 0);
        Assert.assertTrue(Double.compare(new Double(20.0), jpMorgan.getTickerPrice())== 0);
    }
}
