package com.gogoaren.indarra.serviceweatherstatistic.statistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class WeatherComparators {

    Comparator<Weather> getTemperatureDescending() {
        return Comparator.comparing(Weather::getTemperature);
    }

    Comparator<Weather> getTemperatureAscending() {
        return (Weather weather1, Weather weather2) -> weather2.getTemperature().compareTo(weather1.getTemperature());
    }

    Comparator<Weather> getWind() {
        return Comparator.comparing(Weather::getWind);
    }
}

