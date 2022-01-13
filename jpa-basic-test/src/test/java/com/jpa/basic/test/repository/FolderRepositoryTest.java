package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Folder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FolderRepositoryTest {

    @Autowired
    FolderRepository folderRepository;

    @Test
    @DisplayName("자가참조 엔티티 insert 테스트")
    public void saveTest() throws Exception {

        // given (A폴더 - <B폴더,C폴더,D폴더>
        Folder a = Folder.builder()
                .name("A")
                .build();

        


        // when


        // then

    }
}
