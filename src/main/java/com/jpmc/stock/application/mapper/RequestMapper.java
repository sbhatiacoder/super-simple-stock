package com.jpmc.stock.application.mapper;

import com.jpmc.stock.application.model.RequestTrade;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockType;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.model.TradeIndicator;

public class RequestMapper {
	
	public static Trade mapTradeRequest(RequestTrade requestTrade){
		TradeIndicator tradeIndicator = TradeIndicator.getTradeIndicator(requestTrade.getIndicator());
		SimpleStockType simpleStockType = SimpleStockType.getSimpleStockType(requestTrade.getSimpleStockType());
		SimpleStock simpleStock = new SimpleStock(simpleStockType, requestTrade.getSimpleStockSymbol());
		return new Trade(tradeIndicator, simpleStock);
	}

}
