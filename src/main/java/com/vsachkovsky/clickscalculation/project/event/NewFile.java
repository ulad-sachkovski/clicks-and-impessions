package com.vsachkovsky.clickscalculation.project.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewFile {

    private String path;
}
