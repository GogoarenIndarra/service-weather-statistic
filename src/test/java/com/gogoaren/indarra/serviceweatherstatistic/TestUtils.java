package com.gogoaren.indarra.serviceweatherstatistic;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;

import java.math.BigDecimal;

public class TestUtils {

    public static Weather createWeather(final String cityName, final BigDecimal temperature, final double wind) {
        return Weather.builder()
                .city(cityName)
                .humidity(0.0)
                .temperature(temperature)
                .wind(wind)
                .build();
    }
}
