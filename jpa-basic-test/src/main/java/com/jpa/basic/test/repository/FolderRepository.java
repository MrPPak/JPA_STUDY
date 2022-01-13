package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
