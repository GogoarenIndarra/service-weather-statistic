package com.gogoaren.indarra.serviceweatherstatistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import org.junit.Assert;
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

    private String cityName = "Madrid";

    @BeforeEach
    public void setUp() {
        IntStream.range(0, 40).forEach(i -> statisticService.supplyWeather(TestUtils.
                createWeather(cityName + Integer.toString(i), BigDecimal.valueOf(i), i)));
//        for (int i = 0; i < 40; i++) {
//            statisticService.supplyWeather(TestUtils.
//                    createWeather(cityName + Integer.toString(i), BigDecimal.valueOf(i), i));
//        }
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
        Assert.assertEquals(topWarmestCity, topWarmestCities.get(9).getCity());
        Assert.assertEquals(lowWarmestCity, topWarmestCities.get(0).getCity());
        Assert.assertEquals(topColdestCity, topColdestCities.get(9).getCity());
        Assert.assertEquals(lowColdestCity, topColdestCities.get(0).getCity());
        Assert.assertEquals(topWindiestCity, topWindiestCities.get(9).getCity());
        Assert.assertEquals(lowWindiestCity, topWindiestCities.get(0).getCity());
    }

}
