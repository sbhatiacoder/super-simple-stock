package com.jpmc.stock.application.model;

import java.io.Serializable;

/*
 * Domain model represent Trade request
 * */

public class RequestTrade implements Serializable{
	
	private String indicator;
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getSimpleStockSymbol() {
		return simpleStockSymbol;
	}
	public void setSimpleStockSymbol(String simpleStockSymbol) {
		this.simpleStockSymbol = simpleStockSymbol;
	}
	public String getSimpleStockType() {
		return simpleStockType;
	}
	public void setSimpleStockType(String simpleStockType) {
		this.simpleStockType = simpleStockType;
	}
	private String simpleStockSymbol;
	private String simpleStockType;
	
}