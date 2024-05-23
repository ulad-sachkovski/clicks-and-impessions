package com.vsachkovsky.clickscalculation.test.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public NewTopic viewsTopic() {
        return TopicBuilder.name("views")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clicksTopic() {
        return TopicBuilder.name("clicks")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
