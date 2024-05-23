package com.vsachkovsky.clickscalculation.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "FILE_MAP")
@NoArgsConstructor
@AllArgsConstructor
public class FileMap implements Serializable {

    @Id
    private String id;
    private String action;
    private LocalDateTime dateTime;
    private String path;
}
