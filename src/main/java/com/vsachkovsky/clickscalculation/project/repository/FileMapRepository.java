package com.vsachkovsky.clickscalculation.project.repository;

import com.vsachkovsky.clickscalculation.project.domain.FileMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapRepository extends JpaRepository<FileMap, String> {

}
