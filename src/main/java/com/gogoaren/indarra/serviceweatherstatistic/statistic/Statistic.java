package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Statistic {

    Comparator<Weather> weatherComparator;
    Queue<Weather> weatherStatisticQueue;

    public Statistic(final Comparator<Weather> weatherComparator, final int recordsNumber) {
        this.weatherComparator = weatherComparator;
        weatherStatisticQueue = new PriorityQueue<>(recordsNumber, weatherComparator);
    }

    public void supplyWeather(final Weather weather) {
        if (weatherStatisticQueue.size() < 10) {
            weatherStatisticQueue.add(weather);
            return;
        }
        if (weatherComparator.compare(weather, weatherStatisticQueue.peek()) == 1) {
            weatherStatisticQueue.add(weather);
            weatherStatisticQueue.poll();
        }

    }

    public List<Weather> getSortedStatisticData() {
        return weatherStatisticQueue.stream().sorted(weatherComparator).collect(Collectors.toList());
    }
}
