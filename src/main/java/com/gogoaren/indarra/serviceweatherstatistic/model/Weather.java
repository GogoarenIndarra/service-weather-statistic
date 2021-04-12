package com.gogoaren.indarra.serviceweatherstatistic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
