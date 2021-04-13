package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
@Slf4j
public class TemperatureService {

    Comparator<Weather> weatherComparator;
    Queue<Weather> weatherStatisticQueueTop10;

    public TemperatureService(Comparator<Weather> weatherComparator) {
        this.weatherComparator = weatherComparator;
        weatherStatisticQueueTop10 = new PriorityQueue<>(10, weatherComparator);
    }

    public void supplyWeather(Weather weather) {
        if (weatherStatisticQueueTop10.size() < 10) {
            weatherStatisticQueueTop10.add(weather);
            return;
        }
        if (weatherComparator.compare(weather, weatherStatisticQueueTop10.peek()) == -1) {
            weatherStatisticQueueTop10.add(weather);
            weatherStatisticQueueTop10.poll();
        }

        log.info("msg from comperator" + weatherStatisticQueueTop10.toString());

    }


}
