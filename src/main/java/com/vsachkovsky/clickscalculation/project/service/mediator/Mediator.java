package com.vsachkovsky.clickscalculation.project.service.mediator;

import com.vsachkovsky.clickscalculation.project.domain.FileMap;
import com.vsachkovsky.clickscalculation.project.event.NewFile;
import com.vsachkovsky.clickscalculation.project.kafka.KafkaPublisher;
import com.vsachkovsky.clickscalculation.project.pojo.UserAgentCount;
import com.vsachkovsky.clickscalculation.project.service.file.FileProcessor;
import com.vsachkovsky.clickscalculation.project.service.oracle.FileMapService;
import com.vsachkovsky.clickscalculation.project.spark.SparkSqlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Mediator {

    private final FileProcessor fileProcessor;
    private final FileMapService fileMapService;
    private final SparkSqlService sparkSqlService;
    private final KafkaPublisher kafkaPublisher;

    @EventListener(NewFile.class)
    public void processNewFileEvent(NewFile newFile) {
        log.info("new file found: {}", newFile.getPath());
        FileMap fileMap = fileProcessor.processNewFile(newFile);
        fileMapService.find(fileMap)
                .ifPresentOrElse(fileMap1 -> log.info("File with path: {} was already processed", fileMap1.getPath())
                        , () -> {
                            fileMapService.save(fileMap);
                            List<UserAgentCount> userAgentCounts = sparkSqlService.countClicksAndImpressions(fileMap);
                            kafkaPublisher.sendMessage(userAgentCounts);
                        });
    }
}
