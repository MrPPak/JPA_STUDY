package com.jpa.basic.test.folder;

import com.jpa.basic.test.folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
