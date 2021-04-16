package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import lombok.AllArgsConstructor;
import org.codehaus.jackson.map.util.Comparators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StatisticServiceConfiguration {

    WeatherComparators weatherComparators;


    @Bean
    Map<StatisticType, Statistic> weatherStatisticsMap(final @Value("${statistic.record.size}") int size) {

        Map<StatisticType, Statistic> statisticMap = new HashMap<>();
        statisticMap.put(StatisticType.WARMEST_CITIES, new Statistic(weatherComparators.getTemperatureDescending(), size));
        statisticMap.put(StatisticType.COLDEST_CITIES, new Statistic(weatherComparators.getTemperatureAscending(), size));
        statisticMap.put(StatisticType.WINDIEST_CITIES, new Statistic(weatherComparators.getWind(), size));

        return statisticMap;
    }
    /*  Instead of creating 3 beans, better is create Map of Statistics with key of Statistic Type */
//    @Bean
//    Statistic statisticTempMax() {
//        return new Statistic(weatherComparators.getTemperatureAscending(), 10);
//    }
//
//    @Bean
//    Statistic statisticTempMin() {
//        return new Statistic(weatherComparators.getTemperatureDescending(), 10);
//    }
//
//    @Bean
//    Statistic statisticWind() {
//        return new Statistic(weatherComparators.getWind(), 10);
//    }


}
