package com.vsachkovsky.clickscalculation.test.repository;

import com.vsachkovsky.clickscalculation.test.domain.FileMap;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface FileMapRepository extends JpaRepository<FileMap, String> {

}
