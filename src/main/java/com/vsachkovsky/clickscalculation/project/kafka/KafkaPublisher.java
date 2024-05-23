package com.vsachkovsky.clickscalculation.project.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsachkovsky.clickscalculation.project.pojo.UserAgentCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(List<UserAgentCount> userAgentCounts) {
        userAgentCounts.forEach(userAgentCount -> {
            String topic = userAgentCount.getActionType();
            String key = userAgentCount.getUserAgent();
            CountMessage countMessage = new CountMessage(userAgentCount.getCount(), userAgentCount.getDateTime());
            String message;
            try {
                message = objectMapper.writeValueAsString(countMessage);
                log.info("sending message to the topic: {} with key: {} and value: {}", topic, key, message);
                kafkaTemplate.send(topic, key, message);
            } catch (JsonProcessingException e) {
                log.error("Can not convert message: {} to String", countMessage);
            }
        });
    }
}
