package com.jpmc.stock.application;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.mapper.RequestMapper;
import com.jpmc.stock.application.model.RequestTrade;
import com.jpmc.stock.application.service.ISimpleStockService;
import com.jpmc.stock.application.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/stock")
public class SuperSimpleStockController {
  
  @Autowired
  private ISimpleStockService simpleStockService;
  @Autowired
  private ITradeService tradeService;
  @GetMapping
  public ResponseEntity calculateDividentYield(@RequestParam String stockSymbol, @RequestParam double price, @RequestParam final String calculationType)
  throws SimpleStockNotFoundException, SimpleStockServiceException {
    
    switch (calculationType) {
      
    case "DIVIDENT_YIELD":
      return new ResponseEntity < > (
        "Divident yield is - " + simpleStockService.calculateDividendYield(stockSymbol, price),
        HttpStatus.OK);
    case "PE_RATIO":
      return new ResponseEntity < > (
        "P/E Ratio is - " + simpleStockService.calculatePERatio(stockSymbol, price), HttpStatus.OK);
    default:
      return new ResponseEntity < > (
        "Divident yield is - " + simpleStockService.calculateDividendYield(stockSymbol, price),
        HttpStatus.OK);
    }
  }
  @GetMapping(path = "/volStock")
  public ResponseEntity calculateVolumeWeightedStock(@RequestParam String stockSymbol)
  throws SimpleStockNotFoundException {
    
    return new ResponseEntity < > (
      "Volume Weighted Stock is - " + simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol), HttpStatus.OK);
  }
  @PostMapping(path = "/trade")
  public ResponseEntity recordTrade(@RequestBody RequestTrade trade) {
    

    return new ResponseEntity < > (
      "Trade recorded status - " + tradeService.record(RequestMapper.mapTradeRequest(trade)), HttpStatus.OK);
  }

}