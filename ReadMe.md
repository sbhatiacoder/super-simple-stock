# super-simple-stock for JP Morgan

# Requirements

1. Provide working source code that will:-
   a. For a given stock,
   i. Given any price as input, calculate the dividend yield
   ii. Given any price as input, calculate the P/E Ratio
   iii. Record a trade, with timestamp, quantity of shares, buy or sell indicator and
   traded price
   iv. Calculate Volume Weighted Stock Price based on trades in past 15 minutes
   b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

# Solution

The solutions is developed using Java, Spring, Gradle with maven repository usage

# Build commands
gralde build

Run SpringBoot Application class - SuperSimpleStockApplication

# The Source

The source code is mainly divided into three section

1. Domain ( Represents all model objects)
2. Service ( Represents all code business logic to solve the test )
3. Repository ( In memory database to store business objects )

Solution has been build with Spring boot and has a layered architecture, Made it loose coupled so that individual test
in parts can be performed

# The Test

There are test cases to test the business logic and assert the results against the expected acceptance criteria.

They are written using Spring, jUnit and Mockito

API contracts

a. For a given stock,
i. Given any price as input, calculate the dividend yield
--> /stock?stockSymbol=GIN&price=1000&calculationType=DIVIDEND_YIELD

ii. Given any price as input, calculate the P/E Ratio
--> /stock?stockSymbol=GIN&price=1000&calculationType=PE_RATIO

iii. Record a trade, with timestamp, quantity of shares, buy or sell indicator and
traded price
/stock/trade
POST Request
{
"simpleStockSymbol": "TEA",
"tradeIndicator": "BUY",
"tradedPrice":40,
"shareQuantity":2
}

iv. Calculate Volume Weighted Stock Price based on trades in past 15 minutes
/stock/volStock?stockSymbol=GIN&minutes=15

b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
/stock/gbceIndex
