package com.gogoaren.indarra.serviceweatherstatistic.controllers;

import com.gogoaren.indarra.serviceweatherstatistic.TestUtils;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType.WARMEST_CITIES;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class StatisticControllerTest {

    private StatisticService statisticService;

    private MockMvc mockMvc;
    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    void setUp() {
        statisticService = mock(StatisticService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new StatisticController(statisticService)).build();
    }


    @Test
    void shouldReturnListWhenStatisticServiceReturnValidResuult() throws Exception {
        //given
        String exceptedCity = "London";
        String expectedTemperature = "123";
        BigDecimal exceptedTemp = new BigDecimal(expectedTemperature);
        var listWeather = List.of(TestUtils.createWeather(exceptedCity, exceptedTemp));
        when(statisticService.getStatistic(StatisticType.WARMEST_CITIES))
                .thenReturn(listWeather);
        //when

        mockMvc.perform((MockMvcRequestBuilders
                .get("/api/statistic/{type}", WARMEST_CITIES.toString())
                .accept(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].city").value(exceptedCity))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].temperature").value(expectedTemperature));

    }


    @Test
    void shouldReturnBadRequestWhenServiceThrowException() throws Exception {
        //given
        when(statisticService.getStatistic(StatisticType.WARMEST_CITIES))
                .thenThrow(IllegalArgumentException.class);
        //then
        mockMvc.perform((MockMvcRequestBuilders
                .get("/api/statistic/{type}", WARMEST_CITIES.toString())
                .accept(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
