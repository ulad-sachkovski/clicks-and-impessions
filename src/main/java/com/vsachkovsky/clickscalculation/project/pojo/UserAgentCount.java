package com.vsachkovsky.clickscalculation.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class UserAgentCount implements Serializable {

    private String actionType;
    private String userAgent;
    private long count;
    private LocalDateTime dateTime;
}
