package com.vsachkovsky.clickscalculation.project.service.oracle;

import com.vsachkovsky.clickscalculation.project.domain.FileMap;
import com.vsachkovsky.clickscalculation.project.repository.FileMapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class FileMapService {

    private final FileMapRepository repository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<FileMap> find(FileMap fileMap) {
        return repository.findById(fileMap.getId());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void save(FileMap fileMap) {
        repository.save(fileMap);
        log.info("fileMap with id was successfully saved: {}", fileMap.getId());
    }
}
