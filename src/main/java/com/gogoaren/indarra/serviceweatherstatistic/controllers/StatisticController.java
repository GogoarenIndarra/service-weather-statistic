package com.gogoaren.indarra.serviceweatherstatistic.controllers;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/api/statistic")
@RestController
@AllArgsConstructor
@Slf4j
public class StatisticController {
    StatisticService statisticService;


    @GetMapping(value = "/{type}")
    public List<Weather> getStatisticByType(@RequestBody String type) {

        return statisticService.getStatistic(StatisticType.valueOf(type));
    }
}
