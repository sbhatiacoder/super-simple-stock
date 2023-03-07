package com.jpmc.stock.application.service;

import com.jpmc.stock.application.dao.ISimpleStockRepository;
import com.jpmc.stock.application.dao.StockRepositoryImpl;
import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.SimpleStockSymbol;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.model.TradeIndicator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class TestSimpleStockService {

    ISimpleStockRepository stockRepository = new StockRepositoryImpl();

    @Mock
    ITradeService tradeService;

    ISimpleStockService simpleStockService;

    @Before
    public void setUp() {
        simpleStockService = new SimpleStockServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(simpleStockService, "stockRepository", stockRepository);
        ReflectionTestUtils.setField(simpleStockService, "tradeService", tradeService);
    }

    @Test
    public void testCalculateDividendYield() throws SimpleStockServiceException, SimpleStockNotFoundException {

        //Input Price
        double price = 10.15;

        //Test Stock of type Common
        SimpleStock ale = stockRepository.findStockBySymbol(SimpleStockSymbol.ALE);
        final Double dividendYield = simpleStockService.calculateDividendYield(SimpleStockSymbol.ALE, price);
        assertNotNull(dividendYield);
        assertEquals(dividendYield, Double.valueOf(ale.getLastDividend() / price));


        //Test stock of type Preferred
        SimpleStock gin = stockRepository.findStockBySymbol(SimpleStockSymbol.GIN);
        final Double calculatedDividendYield = simpleStockService.calculateDividendYield(SimpleStockSymbol.GIN, price);
        assertNotNull(calculatedDividendYield);
        assertEquals(calculatedDividendYield, Double.valueOf(gin.getFixedDividend() * gin.getParValue() / price));


    }

    @Test
    public void testCalculatePERatio() throws SimpleStockServiceException, SimpleStockNotFoundException {

        //Given Stocks
        SimpleStock ale = stockRepository.findStockBySymbol(SimpleStockSymbol.ALE);
        SimpleStock gin = stockRepository.findStockBySymbol(SimpleStockSymbol.GIN);

        //Given Price
        double givenPrice = 10.15;

        //Test with Stock of type Common
        final Double commonPERatio = simpleStockService.calculatePERatio(SimpleStockSymbol.ALE, givenPrice);
        assertNotNull(commonPERatio);
        assertEquals(commonPERatio, Double.valueOf(givenPrice / ale.getLastDividend()));


        //Test with Stock of type Preferred
        final Double prefPERatio = simpleStockService.calculatePERatio(SimpleStockSymbol.GIN, givenPrice);
        assertNotNull(prefPERatio);
        assertEquals(prefPERatio, Double.valueOf(givenPrice / gin.getLastDividend()));
    }

    @Test
    public void testCalculateVolumeWeightedStockPrice() throws SimpleStockNotFoundException, InterruptedException {

        //Prepare a Stock and Trades
        createTrades();

        //Test
        double volumeWeightedStockPrice = simpleStockService.calculateVolumeWeightedStockPrice(SimpleStockSymbol.ALE, 15);
        assertEquals(38.0, volumeWeightedStockPrice, 0.0);
    }

    private void createTrades() throws InterruptedException {
        List<Trade> list = new ArrayList<>();
        double price = 1.4;
        int qty = 2;
        for (int i = 0; i < 25; i++) {
            Trade trade = new Trade(SimpleStockSymbol.ALE, TradeIndicator.BUY, price, qty);
            Thread.sleep(1000);
            stockRepository.getRepo().get(SimpleStockSymbol.ALE).getTrades().put(new Date(), trade);
            list.add(trade);
            qty = qty + 2;
            price = price + 2.3;
        }
    }
}
