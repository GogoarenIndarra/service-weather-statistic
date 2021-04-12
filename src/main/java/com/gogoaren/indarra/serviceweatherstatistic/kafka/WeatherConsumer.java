package com.gogoaren.indarra.serviceweatherstatistic.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherConsumer {

    @KafkaListener(topics = "${weather.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenWeather(Object message) {
        log.info("Received Message from WeatherService: " + message);
    }
}
