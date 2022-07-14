package com.gogoaren.indarra.serviceweatherstatistic.controllers;

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

import static com.gogoaren.indarra.serviceweatherstatistic.TestUtils.createWeather;
import static com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType.WARMEST_CITIES;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatisticControllerTest {

    private StatisticService statisticService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        statisticService = mock(StatisticService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new StatisticController(statisticService)).build();
    }

    @Test
    void shouldReturnList_WhenStatisticServiceReturnValidResult() throws Exception {
        //given
        final String exceptedCity = "London";
        final String expectedTemperature = "123";
        final BigDecimal exceptedTemp = new BigDecimal(expectedTemperature);
        final var listWeather = List.of(createWeather(exceptedCity, exceptedTemp, 0.0));
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
    void shouldReturnBadRequest_WhenServiceThrowException() throws Exception {
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

    @Test
    void shouldReturnBadRequest_WhenTypeDoNotBelongToEnumType() throws Exception {
        mockMvc.perform((MockMvcRequestBuilders
                        .get("/api/statistic/{type}", "dummyString")
                        .accept(MediaType.APPLICATION_JSON)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
