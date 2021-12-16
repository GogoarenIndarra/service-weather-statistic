package com.gogoaren.indarra.serviceweatherstatistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class WeatherStatisticIntegrationTest {

    @Autowired
    StatisticService statisticService;

    private final String cityName = "Madrid";

    @BeforeEach
    public void setUp() {
        IntStream.range(0, 40).forEach(i -> statisticService
                .supplyWeather(TestUtils
                        .createWeather(cityName + i, BigDecimal.valueOf(i), i)));
    }

    @Test
    public void weatherStatisticShouldReturnCorrectValue() {
        //given
        String topWarmestCity = cityName + "39";
        String lowWarmestCity = cityName + "30";
        String topColdestCity = cityName + "0";
        String lowColdestCity = cityName + "9";
        String topWindiestCity = cityName + "39";
        String lowWindiestCity = cityName + "30";
        //when
        List<Weather> topWarmestCities = statisticService.getStatistic(StatisticType.WARMEST_CITIES);
        List<Weather> topColdestCities = statisticService.getStatistic(StatisticType.COLDEST_CITIES);
        List<Weather> topWindiestCities = statisticService.getStatistic(StatisticType.WINDIEST_CITIES);

        //then
        Assertions.assertEquals(topWarmestCity, topWarmestCities.get(9).getCity());
        Assertions.assertEquals(lowWarmestCity, topWarmestCities.get(0).getCity());
        Assertions.assertEquals(topColdestCity, topColdestCities.get(9).getCity());
        Assertions.assertEquals(lowColdestCity, topColdestCities.get(0).getCity());
        Assertions.assertEquals(topWindiestCity, topWindiestCities.get(9).getCity());
        Assertions.assertEquals(lowWindiestCity, topWindiestCities.get(0).getCity());
    }

}
