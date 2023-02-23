package com.gogoaren.indarra.serviceweatherstatistic.kafka;


import com.gogoaren.indarra.serviceweatherstatistic.model.Weather;
import com.gogoaren.indarra.serviceweatherstatistic.statistic.StatisticService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherConsumer {

    private final StatisticService statisticService;

    @KafkaListener(topics = "${weather.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenWeather(final Weather message) {
        log.info("Received Message from WeatherService: {}", message);
        statisticService.supplyWeather(message);
    }
}
