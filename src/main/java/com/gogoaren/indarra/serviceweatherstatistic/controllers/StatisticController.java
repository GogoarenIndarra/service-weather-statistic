package com.gogoaren.indarra.serviceweatherstatistic.controllers;

import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/api/statistic")
@RestController
@RequiredArgsConstructor
@Slf4j
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping(value = "/{type}")
    public ResponseEntity<List<Weather>> getStatisticByType(@PathVariable final String type) {
        if (!EnumUtils.isValidEnum(StatisticType.class, type))
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        try {
            return new ResponseEntity<>(statisticService.getStatistic(StatisticType.valueOf(type)), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }
    }
}
