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
        String path = newFile.getPath();
        String[] pathArray = path.split("_");
        String action = pathArray[0];
        String dateTimeString = pathArray[3];
        String id = action + dateTimeString + pathArray[4];
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DATE_FORMAT);
        FileMap fileMap = new FileMap(id, action, dateTime, path);
        log.info("the file was processed with path: {}", fileMap.getPath());
        return fileMap;
    }
}
