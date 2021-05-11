# service description  
This service receives message to Kafka from weather-service, and return statistic of top warmest, coldest and windiest cities:  
http://localhost:5555/api/statistic/WARMEST_CITIES  
http://localhost:5555/api/statistic/COLDEST_CITIES  
http://localhost:5555/api/statistic/WINDIEST_CITIES  

# learning resources  
response entity - https://www.baeldung.com/spring-response-entity  
mock mvc https://www.baeldung.com/integration-testing-in-spring  
kafka for spring: https://www.baeldung.com/spring-kafka  