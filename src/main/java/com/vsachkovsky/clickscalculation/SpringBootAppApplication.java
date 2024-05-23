package com.vsachkovsky.clickscalculation;

import com.vsachkovsky.clickscalculation.project.service.file.FileScanner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@RequiredArgsConstructor
public class SpringBootAppApplication {

    private final FileScanner fileScanner;

    @EventListener(ApplicationReadyEvent.class)
    public void runTheProgram() {
        fileScanner.scanNewFiles();
    }

    public static void main(String[] args) {

        SpringApplication.run(SpringBootAppApplication.class, args);
    }
}
