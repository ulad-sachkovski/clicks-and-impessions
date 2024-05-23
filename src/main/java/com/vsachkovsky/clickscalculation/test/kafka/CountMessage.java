package com.vsachkovsky.clickscalculation.test.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CountMessage {

    private long clicks;
    private LocalDateTime dateTime;
}
