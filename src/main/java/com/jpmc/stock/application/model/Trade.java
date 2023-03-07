package com.jpmc.stock.application.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain model represents the trade in the application
 */
public class Trade implements Serializable {

    private SimpleStockSymbol simpleStockSymbol;
    private TradeIndicator tradeIndicator;
    private Double tradedPrice;
    private Integer shareQuantity;

    private final Date time = new Date();

    public Trade(SimpleStockSymbol simpleStockSymbol, TradeIndicator tradeIndicator, Double tradedPrice, Integer shareQuantity) {
        this.simpleStockSymbol = simpleStockSymbol;
        this.tradeIndicator = tradeIndicator;
        this.tradedPrice = tradedPrice;
        this.shareQuantity = shareQuantity;
    }

    public Trade() {
    }

    public TradeIndicator getTradeIndicator() {
        return tradeIndicator;
    }

    public SimpleStockSymbol getSimpleStockSymbol() {
        return simpleStockSymbol;
    }

    public Double getTradedPrice() {
        return tradedPrice;
    }

    public Integer getShareQuantity() {
        return shareQuantity;
    }

    public Date getTime() {
        return (Date) this.time.clone();
    }

    @Override
    public String toString() {
        return "Trade{" + "indicator=" + tradeIndicator +
                ", stock=" + simpleStockSymbol +
                ", price=" + tradedPrice +
                ", time=" + new Date() +
                ", shareQuantity=" + shareQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;

        Trade trade = (Trade) o;

        if (Double.compare(trade.tradedPrice, tradedPrice) != 0) return false;
        if (Double.compare(trade.shareQuantity, shareQuantity) != 0) return false;
        if (tradeIndicator != trade.tradeIndicator) return false;
        if (time.compareTo(trade.time) != 0) return false;
        return simpleStockSymbol.equals(trade.simpleStockSymbol);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = tradeIndicator.hashCode();
        result = 31 * result + simpleStockSymbol.hashCode();
        temp = tradedPrice.longValue();
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = shareQuantity.longValue();
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
