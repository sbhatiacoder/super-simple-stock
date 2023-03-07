package com.jpmc.stock.application.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the Stock
 */
public class TestSimpleStock {

    private SimpleStock jpMorgan;

    @Before
    public void setUp() {
        jpMorgan = new SimpleStock(SimpleStockSymbol.ALE, SimpleStockType.PREFERRED_STOCK, 1.0, 2.0, 1.0);
    }

    @Test
    public void testStock() {
        assertNotNull(jpMorgan);
        assertNotEquals(jpMorgan, new SimpleStock(SimpleStockSymbol.ALE, SimpleStockType.COMMON_STOCK, 1.0, 2.0, 1.0));

        SimpleStock gin = new SimpleStock(SimpleStockSymbol.ALE, SimpleStockType.PREFERRED_STOCK, 1.0, 2.0, 1.0);
        assertEquals(jpMorgan, gin);

    }
}
