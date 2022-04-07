package com.jpmc.stock.application.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain model represents the trade in the application
 */
public class Trade implements Serializable {

    private final TradeIndicator indicator;
    private final SimpleStock simpleStock;
    private double price = 0.0;
    private Date time;
    private double shareQuantity =0.0;

    public Trade(TradeIndicator indicator, SimpleStock simpleStock) {
        this.indicator = indicator;
        this.simpleStock = simpleStock;
        this.time = new Date();
    }

    public TradeIndicator getIndicator() {
        return indicator;
    }

    public SimpleStock getSimpleStock() {
        return simpleStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getShareQuantity() {
        return shareQuantity;
    }

    public void setShareQuantity(Double shareQuantity) {
        this.shareQuantity = shareQuantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trade{");
        sb.append("indicator=").append(indicator);
        sb.append(", stock=").append(simpleStock);
        sb.append(", price=").append(price);
        sb.append(", time=").append(time);
        sb.append(", shareQuantity=").append(shareQuantity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;

        Trade trade = (Trade) o;

        if (Double.compare(trade.price, price) != 0) return false;
        if (Double.compare(trade.shareQuantity, shareQuantity) != 0) return false;
        if (indicator != trade.indicator) return false;
        if (!simpleStock.equals(trade.simpleStock)) return false;
        if (!time.equals(trade.time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = indicator.hashCode();
        result = 31 * result + simpleStock.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + time.hashCode();
        temp = Double.doubleToLongBits(shareQuantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
