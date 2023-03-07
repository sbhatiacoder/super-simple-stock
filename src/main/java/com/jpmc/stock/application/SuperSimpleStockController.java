package com.jpmc.stock.application;

import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.model.CalculationType;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.service.ISimpleStockService;
import com.jpmc.stock.application.service.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/stock")
public class SuperSimpleStockController {

    @Autowired
    private ISimpleStockService simpleStockService;

    @Autowired
    private ITradeService tradeService;

    @GetMapping
    public ResponseEntity calculateDividendYield(@RequestParam SimpleStockSymbol stockSymbol, @RequestParam double price, @RequestParam final CalculationType calculationType)
            throws SimpleStockNotFoundException, SimpleStockServiceException {

        if (Objects.requireNonNull(calculationType) == CalculationType.PE_RATIO) {
            return new ResponseEntity<>(
                    "P/E Ratio is - " + simpleStockService.calculatePERatio(stockSymbol, price), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "Dividend yield is - " + simpleStockService.calculateDividendYield(stockSymbol, price),
                HttpStatus.OK);
    }

    @GetMapping(path = "/volStock")
    public ResponseEntity calculateVolumeWeightedStock(@RequestParam SimpleStockSymbol stockSymbol, @RequestParam int minutes)
            throws SimpleStockNotFoundException {

        return new ResponseEntity<>(
                "Volume Weighted Stock is - " + simpleStockService.calculateVolumeWeightedStockPrice(stockSymbol, minutes), HttpStatus.OK);
    }

    @PostMapping(path = "/trade")
    public ResponseEntity recordTrade(@RequestBody Trade trade) throws SimpleStockNotFoundException {


        return new ResponseEntity<>(
                "Trade recorded status - " + tradeService.record(trade), HttpStatus.OK);
    }

    @GetMapping(path = "/gbceIndex")
    public ResponseEntity calculateGBCEIndex()
            throws SimpleStockNotFoundException {

        return new ResponseEntity<>(
                "GBCE Share Index is - " + simpleStockService.calculateAllShareIndex(), HttpStatus.OK);
    }

}