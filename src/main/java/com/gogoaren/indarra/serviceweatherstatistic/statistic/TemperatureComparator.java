package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class TemperatureComparator implements Comparator<Weather> {
    @Override
    public int compare(Weather o1, Weather o2) {
        return o1.getTemperature().compareTo(o2.getTemperature()) > 0 ? -1 : 1;
    }
}

