package com.gogoaren.indarra.serviceweatherstatistic;

import com.gogoaren.indarra.serviceweatherstatistic.statistic.Statistic;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatisticServiceTest {

    private StatisticService statisticService;
    private Statistic warmestCityStatisticMock;

    @BeforeEach
    void setUp() {
        warmestCityStatisticMock = mock(Statistic.class);
        Map<StatisticType, Statistic> statistics = new HashMap<>();
        statistics.put(StatisticType.WARMEST_CITIES, warmestCityStatisticMock);
        statisticService = new StatisticService(statistics);
    }

    @Test
    void shouldThrowException_WhenNotSupportedEnumStatisticUsed() {
        final StatisticType notImplementedStatistic = StatisticType.WINDIEST_CITIES;
        final Exception exception = assertThrows(IllegalArgumentException.class, () ->
                statisticService.getStatistic(notImplementedStatistic));

        final String expectedMessage = "Statistic not implement for this type: " + notImplementedStatistic;
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnListOfWeather_WhenStatisticExists() {
        //given
        final String exceptedString = "London";
        final BigDecimal exceptedTemp = new BigDecimal(123);
        when(warmestCityStatisticMock.getSortedStatisticData())
                .thenReturn(List.of(TestUtils.createWeather(exceptedString, exceptedTemp, 0.0)));
        //when
        final var result = statisticService.getStatistic(StatisticType.WARMEST_CITIES);
        //then
        assertEquals(1, result.size());
        assertEquals(result.get(0).getCity(), exceptedString);
        assertEquals(result.get(0).getTemperature(), exceptedTemp);
    }


}