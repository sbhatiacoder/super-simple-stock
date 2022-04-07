package com.jpmc.stock.application.model;

import com.fasterxml.jackson.annotation.JsonCreator;
/**
 * Stock Type available to the application
 */
public enum SimpleStockType {

    COMMON_STOCK, PREFERRED_STOCK;

@JsonCreator
public static SimpleStockType getSimpleStockType(String value){
	for(SimpleStockType stockType: SimpleStockType.values()){

if(stockType.toString().equalsIgnoreCase(value)){
return stockType;
}

}

return null;
}
}
