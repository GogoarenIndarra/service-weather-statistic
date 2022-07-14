package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class StatisticService {

    Map<StatisticType, Statistic> weatherStatisticsMap;

    public void supplyWeather(final Weather weather) {
        weatherStatisticsMap.forEach((k, v) -> v.supplyWeather(weather));
    }

    public List<Weather> getStatistic(final StatisticType type) {
        var statistic = weatherStatisticsMap.get(type);
        Assert.notNull(statistic, "Statistic not implement for this type: " + type);
        return statistic.getSortedStatisticData();

    }
}

