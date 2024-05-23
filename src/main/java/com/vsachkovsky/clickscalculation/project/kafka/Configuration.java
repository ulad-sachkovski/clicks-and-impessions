package com.vsachkovsky.clickscalculation.project.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private static final String VIEW_TOPIC = "views";
    private static final String IMPRESSIONS_TOPIC = "impressions";

    @Bean
    public NewTopic viewsTopic() {
        return TopicBuilder.name(VIEW_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clicksTopic() {
        return TopicBuilder.name(IMPRESSIONS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
