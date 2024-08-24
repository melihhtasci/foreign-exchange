# Foreign Exchange API

Project uses external API for currency conversion which is currencylayer.com.  

Uses redis cache at potential performance gaining points.  
Database is embeded-H2
Includes swagger.


### To run the application
1. Go [currencylayer.com](https://currencylayer.com/) and get access key
2. Change it at docker-compose file
3. Run *docker-compose up* command.

To access swagger, /swagger-ui.html.  
To access h2, /h2-console. Jdbc url is *jdbc:h2:mem:testdb*


### Summary of API endpoints

1. POST - .../api/v1/currency/convert  
Convert currency to target currency with specified amount.  
Return transaction id and save database.   
  

2. GET  - .../api/v1/currency/history  
Get currency from database by transactionId or date  


3. POST - .../api/v1/exchange-rate/convert  
Get exchange rate between currencies.  

### ***Highly recommend to check swagger to get more detail about endpoints*** ###

