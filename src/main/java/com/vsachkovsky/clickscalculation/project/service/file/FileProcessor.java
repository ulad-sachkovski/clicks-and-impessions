package com.vsachkovsky.clickscalculation.project.service.file;

import com.vsachkovsky.clickscalculation.project.domain.FileMap;
import com.vsachkovsky.clickscalculation.project.event.NewFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class FileProcessor {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public FileMap processNewFile(NewFile newFile) {
        String[] s = newFile.getPath().split("_");
        String action = s[0];
        String dateTimeString = s[3];
        String id = action + dateTimeString + s[4];
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATE_FORMAT);
        String path = newFile.getPath();
        FileMap fileMap = new FileMap(id, action, dateTime, path);
        log.info("the file was processed: {}", fileMap);
        return fileMap;
    }
}
