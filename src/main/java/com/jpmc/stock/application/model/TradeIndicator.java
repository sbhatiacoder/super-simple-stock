package com.jpmc.stock.application.model;

/**
 * The Trade indicator
 */
public enum TradeIndicator {
    BUY, SELL;

    public static TradeIndicator getTradeIndicator(String value){

        for(TradeIndicator tradeIndicator : TradeIndicator.values()){
            if(value.equalsIgnoreCase(tradeIndicator.toString())){
                return tradeIndicator;
            }
        }
return null;
    }
}
