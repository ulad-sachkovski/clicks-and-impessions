package com.vsachkovsky.clickscalculation.project.service.file;

import com.vsachkovsky.clickscalculation.project.event.NewFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileScanner {

    private static final String PATH_TO_FOLDER = "raw_data";
    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    @SuppressWarnings("unchecked")
    public void scanForNewFiles() {
        try {
            Path path = Paths.get(PATH_TO_FOLDER);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, ENTRY_CREATE);
            scanForExistingFiles(path);
            log.info("Monitoring directory for new files: {}", path);
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == OVERFLOW) {
                        continue;
                    }
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    if (kind == ENTRY_CREATE) {
                        log.info("New file created: {}", fileName);
                        applicationEventPublisher.publishEvent(new NewFile(fileName.toString()));
                    }
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            log.error("Error is: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void scanForExistingFiles(Path path) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                log.info("Existing file: {}", entry.getFileName());
                applicationEventPublisher.publishEvent(new NewFile(entry.getFileName().toString()));
            }
        } catch (IOException e) {
            log.error("Error is: {}", e.getMessage());
        }
    }
}
