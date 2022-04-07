package com.jpmc.stock.application.model;

/**
 * Represents the SimpleStock in the application
 */
public class SimpleStock {

    private final SimpleStockType SimpleStockType;
    private final String SimpleStockSymbol;
    private double lastDividend = 0.0;
    private double fixedDividend = 0.0;
    private double parValue = 0.0;
    private double tickerPrice = 0.0;

    public SimpleStock(SimpleStockType SimpleStockType, String SimpleStockSymbol) {
        this.SimpleStockType = SimpleStockType;
        this.SimpleStockSymbol = SimpleStockSymbol;
    }

    public SimpleStock(String symbol, SimpleStockType type, Double lastDividend, Double fixedDividend, Double parValue) {
        this.SimpleStockSymbol = symbol;
        this.SimpleStockType = type;
        this.setLastDividend(lastDividend);
        this.setFixedDividend(fixedDividend);
        this.setParValue(parValue);
    }
    public SimpleStockType getSimpleStockType() {
        return SimpleStockType;
    }

    public String getSimpleStockSymbol() {
        return SimpleStockSymbol;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        this.parValue = parValue;
    }


    public double getTickerPrice() {
        return tickerPrice;
    }

    public void setTickerPrice(double tickerPrice) {
        this.tickerPrice = tickerPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleStock{");
        sb.append("SimpleStockType=").append(SimpleStockType);
        sb.append(", SimpleStockSymbol='").append(SimpleStockSymbol).append('\'');
        sb.append(", lastDividend=").append(lastDividend);
        sb.append(", fixedDividend=").append(fixedDividend);
        sb.append(", parValue=").append(parValue);
        sb.append(", tickerPrice=").append(tickerPrice);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleStock)) return false;

        SimpleStock SimpleStock = (SimpleStock) o;

        if (Double.compare(SimpleStock.fixedDividend, fixedDividend) != 0) return false;
        if (Double.compare(SimpleStock.lastDividend, lastDividend) != 0) return false;
        if (Double.compare(SimpleStock.parValue, parValue) != 0) return false;
        if (Double.compare(SimpleStock.tickerPrice, tickerPrice) != 0) return false;
        if (!SimpleStockSymbol.equals(SimpleStock.SimpleStockSymbol)) return false;
        if (SimpleStockType != SimpleStock.SimpleStockType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = SimpleStockType.hashCode();
        result = 31 * result + SimpleStockSymbol.hashCode();
        temp = Double.doubleToLongBits(lastDividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fixedDividend);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(parValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tickerPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
