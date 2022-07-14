package com.gogoaren.indarra.serviceweatherstatistic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class Weather {

    @JsonProperty("city")
    private String city;
    @JsonProperty("temperature")
    private BigDecimal temperature;
    @JsonProperty("humidity")
    private double humidity;
    @JsonProperty("wind")
    private double wind;
    @JsonProperty("country")
    private String country;
}
