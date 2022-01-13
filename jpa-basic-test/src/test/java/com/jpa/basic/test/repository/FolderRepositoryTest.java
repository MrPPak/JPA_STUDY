package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Folder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class FolderRepositoryTest {

    @Autowired
    FolderRepository folderRepository;

    @Test
    @DisplayName("자가참조 엔티티 insert 테스트")
    public void saveTest() throws Exception {

        // given (A폴더 - <B폴더,C폴더,D폴더>
        Folder aFolder = Folder.builder()
                .name("A")
                .build();

        Folder bFolder = Folder.builder()
                .name("B")
                .parent(aFolder)
                .build();

        Folder cFolder = Folder.builder()
                .name("C")
                .parent(aFolder)
                .build();

        Folder dFolder = Folder.builder()
                .name("D")
                .parent(aFolder)
                .build();

        Folder eFolder = Folder.builder()
                .name("E")
                .parent(bFolder)
                .build();

        // when
        folderRepository.saveAll(Arrays.asList(aFolder, bFolder, cFolder, dFolder, eFolder));
    }


}
