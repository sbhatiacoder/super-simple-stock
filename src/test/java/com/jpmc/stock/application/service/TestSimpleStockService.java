package com.jpmc.stock.application.service;

import com.jpmc.stock.application.dao.ISimpleStockRepository;
import com.jpmc.stock.application.exception.SimpleStockNotFoundException;
import com.jpmc.stock.application.exception.SimpleStockServiceException;
import com.jpmc.stock.application.model.SimpleStock;
import com.jpmc.stock.application.model.Trade;
import com.jpmc.stock.application.model.TradeIndicator;
import com.jpmc.stock.application.service.ISimpleStockService;
import com.jpmc.stock.application.service.ITradeService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TestSimpleStockService extends AbstractSpringTest {

    @Autowired
    ISimpleStockRepository stockRepository;
    @Mock
    ITradeService tradeService;

    @InjectMocks
    @Resource
    private ISimpleStockService simpleStockService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateDividendYield() throws SimpleStockServiceException, SimpleStockNotFoundException {

        //Input Price
        double price = 10.15;

        //Test Stock of type Common
        SimpleStock ale = stockRepository.findStockBySymbol("ALE");
        final Double dividendYield = simpleStockService.calculateDividendYield(ale.getSimpleStockSymbol(), price);
        Assert.assertNotNull(dividendYield);
        Assert.assertEquals(dividendYield, new Double(ale.getLastDividend() / price));


        //Test stock of type Preferred
        SimpleStock gin = stockRepository.findStockBySymbol("GIN");
        final Double calculatedDividendYield = simpleStockService.calculateDividendYield(gin.getSimpleStockSymbol(), price);
        Assert.assertNotNull(calculatedDividendYield);
        Assert.assertEquals(calculatedDividendYield, new Double(gin.getFixedDividend() * gin.getParValue() / price));


    }

    @Test
    public void testCalculatePERatio() throws SimpleStockServiceException, SimpleStockNotFoundException {

        //Given Stocks
    	SimpleStock ale = stockRepository.findStockBySymbol("ALE");
    	SimpleStock gin = stockRepository.findStockBySymbol("GIN");

        //Given Price
        double givenPrice = 10.15;

        //Test with Stock of type Common
        final Double commonPERatio = simpleStockService.calculatePERatio(ale.getSimpleStockSymbol(), givenPrice);
        Assert.assertNotNull(commonPERatio);
        Assert.assertEquals(commonPERatio, new Double(givenPrice / ale.getLastDividend()));


        //Test with Stock of type Preferred
        final Double prefPERatio = simpleStockService.calculatePERatio(gin.getSimpleStockSymbol(), givenPrice);
        Assert.assertNotNull(prefPERatio);
        Assert.assertEquals(prefPERatio, new Double(givenPrice / gin.getLastDividend()));
    }


    @Test
    public void testCalculateGBCEAllShareIndex() throws SimpleStockServiceException, SimpleStockNotFoundException {

        //Prepare input of Stocks with price
        List<SimpleStock> stocks = stockRepository.findAll();
        double price = 2;
        for (SimpleStock stock : stocks) {
            stock.setTickerPrice(price);
            price = price * 2;
        }

        //Calculate the GBCE
        final Double index = simpleStockService.calculateAllShareIndex(stocks);

        //Assert
        Assert.assertEquals(index, new Double(8.0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateGBCEAllShareIndexThrowsStockServiceException() {

        //The service should throw exception when passing empty stock list
        simpleStockService.calculateAllShareIndex(new ArrayList<SimpleStock>());
    }


    @Test
    public void testCalculateVolumeWeightedStockPrice() throws SimpleStockNotFoundException {

        //Prepare a Stock and Trades
    	SimpleStock stock = stockRepository.findStockBySymbol("ALE");
        List<Trade> list = createTrades(stock);

        //Set Expectation
        Mockito.when(tradeService.findAllTradesByStock(stock)).thenReturn(list);

        //Test
        double volumeWeightedStockPrice = simpleStockService.calculateVolumeWeightedStockPrice(stock.getSimpleStockSymbol());
        Assert.assertNotNull(volumeWeightedStockPrice);
        Assert.assertTrue(volumeWeightedStockPrice ==  new Double(21.0).doubleValue());
    }

    private List<Trade> createTrades(SimpleStock stock) {
        List<Trade> list = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        double price = 1.4;
        double qty = 2;
        for (int i = 0; i < 25; i++) {
            Trade trade = new Trade(TradeIndicator.BUY, stock);
            instance.add(Calendar.MINUTE, -1);
            trade.setTime(instance.getTime());
            trade.setShareQuantity(qty);
            trade.setPrice(price);
            list.add(trade);
            qty = qty + 2;
            price = price + 2.3;
        }
        return list;
    }

}
