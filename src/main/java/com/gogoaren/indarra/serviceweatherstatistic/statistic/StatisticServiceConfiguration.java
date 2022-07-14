package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StatisticServiceConfiguration {

    private final WeatherComparators weatherComparators;

    @Bean
    Map<StatisticType, Statistic> weatherStatisticsMap(final @Value("${statistic.record.size}") int size) {

        final Map<StatisticType, Statistic> statisticMap = new HashMap<>();
        statisticMap.put(StatisticType.WARMEST_CITIES, new Statistic(weatherComparators.getTemperatureDescending(), size));
        statisticMap.put(StatisticType.COLDEST_CITIES, new Statistic(weatherComparators.getTemperatureAscending(), size));
        statisticMap.put(StatisticType.WINDIEST_CITIES, new Statistic(weatherComparators.getWind(), size));

        return statisticMap;
    }
}
