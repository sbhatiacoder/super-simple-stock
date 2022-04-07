# super-simple-stock for JP Morgan

# Requirements

1. Provide the source code for an application that will:-
a. For a given stock,

1. Given any price as input, calculate the dividend yield
2. Given any price as input, calculate the P/E Ratio
3. Record a trade, with timestamp, quantity, buy or sell indicator and price
4. Calculate Volume Weighted Stock Price based on trades in past 5 minutes

b. Calculate the GBCE All Share Index using the geometric mean of the Volume Weighted Stock Price for all stocks

# Solution

The solutions is developed using Java, Spring, Gradle with maven repository usage

# The Source
The source code is mainly divided into three section

1. Domain ( Represents all model objects)
2. Service ( Represents all code business logic to solve the test )
3. Repository ( In memory database to store business objects )

Solution has been build with Spring boot and has has a layored architecture, Made it loose coupled so that individual test in parts can be performed

# The Test
There are test cases to test the business logic and assert the results against the expected acceptance criteria. 

They are written using Spring, jUnit and Mockito 

API contracts

/stock?stockSymbol=GIN&price=1000&calculationType=DIVIDENT_YIELD

/stock?stockSymbol=GIN&price=1000&calculationType=PE_RATIO

/stock/volStock?stockSymbol=GIN

/stock/trade
POST Request
{
		"indicator":"BUY",
		"simpleStockType":"COMMON_STOCK",
		"simpleStockSymbol":"TEA"
}



