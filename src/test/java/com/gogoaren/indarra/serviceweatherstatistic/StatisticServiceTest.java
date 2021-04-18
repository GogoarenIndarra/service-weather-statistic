package com.gogoaren.indarra.serviceweatherstatistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.Statistic;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticServiceTest {

    private StatisticService statisticService;
    private Statistic warmestCityStatisticMock;

    @BeforeEach
    public void setUp() {
        warmestCityStatisticMock = mock(Statistic.class);
        Map<StatisticType, Statistic> statistics = new HashMap<>();
        statistics.put(StatisticType.WARMEST_CITIES, warmestCityStatisticMock);
        statisticService = new StatisticService(statistics);
    }

    @Test
    public void shouldThrowExceptionWhenNotSupportedEnumStatisticUsed() {
        StatisticType notImplementedStatistic = StatisticType.WINDIEST_CITIES;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                statisticService.getStatistic(notImplementedStatistic));

        String expectedMessage = "Statistic not implement for this type: " + notImplementedStatistic;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnListOfWeatherWhenStatisticExists() {
        //given
        String exceptedString = "London";
        BigDecimal exceptedTemp = new BigDecimal(123);
        when(warmestCityStatisticMock.getSortedStatisticData())
                .thenReturn(List.of(TestUtils.createWeather(exceptedString, exceptedTemp, 0.0)));
        //when
        var result = statisticService.getStatistic(StatisticType.WARMEST_CITIES);
        //then
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCity(), exceptedString);
        assertEquals(result.get(0).getTemperature(), exceptedTemp);
    }



}