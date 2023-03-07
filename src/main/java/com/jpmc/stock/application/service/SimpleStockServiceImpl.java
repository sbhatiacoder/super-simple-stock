package com.jpmc.stock.application.service;

import com.jpmc.stock.application.dao.ISimpleStockRepository;
import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.SimpleStockType;
import com.jpmc.stock.application.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Implementation of stock service
 */
@Service
public class SimpleStockServiceImpl implements ISimpleStockService {

    private static final Logger LOG = Logger.getLogger("SimpleStockServiceImpl");

    @Autowired
    private ISimpleStockRepository stockRepository;

    @Autowired
    ITradeService tradeService;

    @Override
    public Double calculateDividendYield(SimpleStockSymbol stockSymbol,
                                         Double price) throws SimpleStockServiceException, SimpleStockNotFoundException {

        Double dividendYield = 0.0;
        //Find the stock by symbol
        final SimpleStock stock = stockRepository.findStockBySymbol(stockSymbol);
        if (stock == null) {
            throw new SimpleStockNotFoundException("Cannot find stock for symbol " + stockSymbol);
        }
        try {
            final SimpleStockType stockType = stock.getSimpleStockType();
            switch (stockType) {
                case COMMON_STOCK:
                    dividendYield = stock.getLastDividend() / price;
                    break;
                case PREFERRED_STOCK:
                    dividendYield = (stock.getFixedDividend() * stock.getParValue()) / price;
                    break;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new SimpleStockServiceException("Cannot calculate dividend yield");
        }
        return dividendYield;

    }

    @Override
    public Double calculatePERatio(SimpleStockSymbol stockSymbol,
                                   Double price) throws SimpleStockServiceException, SimpleStockNotFoundException {
        final SimpleStock stock = stockRepository.findStockBySymbol(stockSymbol);
        try {
            return price / stock.getLastDividend();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new SimpleStockServiceException("Cannot calculate PE ratio");
        }
    }

    @Override
    public Double calculateVolumeWeightedStockPrice(SimpleStockSymbol stockSymbol, int minutes) throws SimpleStockNotFoundException {
        SimpleStock stock = stockRepository.findStockBySymbol(stockSymbol);

        Date now = new Date();
        // Date 15 minutes ago
        Date startTime = new Date(now.getTime() - (minutes * 60 * 1000));
        // Get trades for the last 15 minutes
        SortedMap<Date, Trade> tradesList = stock.getTrades().tailMap(startTime);
        // Calculate
        Double volumeWeigthedStockPrice = 0.0;
        Integer totalQuantity = 0;
        for (Trade trade : tradesList.values()) {
            totalQuantity += trade.getShareQuantity();
            volumeWeigthedStockPrice += trade.getTradedPrice() * trade.getShareQuantity();
        }
        return Double.isNaN(volumeWeigthedStockPrice / totalQuantity) ? 0.0 : Math.round(volumeWeigthedStockPrice / totalQuantity);
    }

    @Override
    public Map<String, Double> calculateAllShareIndex() throws SimpleStockNotFoundException {
        Map<String, Double> gbceIndex = new HashMap<>();
        for (SimpleStock simpleStock : stockRepository.getRepo().values()) {
            Double sum = 0.0;
            for (Trade trade : simpleStock.getTrades().values()) {
                sum += trade.getTradedPrice();
            }
            gbceIndex.put(simpleStock.getSimpleStockSymbol().toString(), StrictMath.pow(sum, 1.0 / simpleStock.getTrades().values().size()));
        }
        return gbceIndex;
    }
}
