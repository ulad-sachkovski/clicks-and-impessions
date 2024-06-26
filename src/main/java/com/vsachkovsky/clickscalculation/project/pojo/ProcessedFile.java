package com.vsachkovsky.clickscalculation.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ProcessedFile {

    private String action;
    private LocalDateTime dateTime;
    private String id;
}
