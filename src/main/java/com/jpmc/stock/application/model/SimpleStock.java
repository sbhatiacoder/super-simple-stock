package com.jpmc.stock.application.model;

import java.util.Date;
import java.util.TreeMap;

/**
 * Represents the SimpleStock in the application
 */
public class SimpleStock {

    private final SimpleStockType simpleStockType;

    private final SimpleStockSymbol simpleStockSymbol;

    private final Double lastDividend;
    private final Double fixedDividend;
    private final Double parValue;

    private final TreeMap<Date, Trade> trades;

    public SimpleStock(SimpleStockSymbol simpleStockSymbol, SimpleStockType simpleStockType, Double lastDividend, Double fixedDividend, Double parValue) {
        this.simpleStockSymbol = simpleStockSymbol;
        this.simpleStockType = simpleStockType;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
        this.trades = new TreeMap<>();
    }

    public SimpleStockType getSimpleStockType() {
        return simpleStockType;
    }

    public TreeMap<Date, Trade> getTrades() {
        return trades;
    }

    public SimpleStockSymbol getSimpleStockSymbol() {
        return simpleStockSymbol;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public Double getParValue() {
        return parValue;
    }

    @Override
    public String toString() {
        return "SimpleStock{" + "SimpleStockSymbol=" + simpleStockSymbol +
                "SimpleStockType=" + simpleStockType +
                ", lastDividend=" + lastDividend +
                ", fixedDividend=" + fixedDividend +
                ", parValue=" + parValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleStock)) return false;

        SimpleStock SimpleStock = (SimpleStock) o;
        if (simpleStockSymbol != SimpleStock.simpleStockSymbol) return false;
        if (Double.compare(SimpleStock.fixedDividend, fixedDividend) != 0) return false;
        if (Double.compare(SimpleStock.lastDividend, lastDividend) != 0) return false;
        if (Double.compare(SimpleStock.parValue, parValue) != 0) return false;
        return simpleStockType == SimpleStock.simpleStockType;
    }

}
