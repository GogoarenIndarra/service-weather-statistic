package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;


public class Statistic {

    Comparator<Weather> weatherComparator;
    Queue<Weather> weatherStatisticQueue;


    public Statistic(Comparator<Weather> weatherComparator, int recordsNumber) {
        this.weatherComparator = weatherComparator;
        weatherStatisticQueue = new PriorityQueue<>(recordsNumber, weatherComparator);
    }

    public void supplyWeather(Weather weather) {
        if (weatherStatisticQueue.size() < 10) {
            weatherStatisticQueue.add(weather);
            return;
        }
        if (weatherComparator.compare(weather, weatherStatisticQueue.peek()) == -1) {
            weatherStatisticQueue.add(weather);
            weatherStatisticQueue.poll();
        }

    }

    public List<Weather> getSortedStatisticData() {
        return weatherStatisticQueue.stream().sorted(weatherComparator).collect(Collectors.toList());
    }
}
