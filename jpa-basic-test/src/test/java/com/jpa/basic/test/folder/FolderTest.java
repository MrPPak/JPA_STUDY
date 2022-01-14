package com.jpa.basic.test.folder;

import com.jpa.basic.test.folder.Folder;
import com.jpa.basic.test.folder.FolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@SpringBootTest
public class FolderTest {

    @Autowired
    FolderRepository folderRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {

        Folder aFolder = Folder.builder()
                .name("A")
                .build();

        Folder bFolder = Folder.builder()
                .name("B")
                .parent(aFolder)
                .build();

        Folder bChild1 = Folder.builder()
                .name("BChild1")
                .parent(bFolder)
                .build();

        Folder bChild2 = Folder.builder()
                .name("BChild2")
                .parent(bFolder)
                .build();

        Folder bChild3 = Folder.builder()
                .name("BChild3")
                .parent(bFolder)
                .build();

        Folder cFolder = Folder.builder()
                .name("C")
                .parent(aFolder)
                .build();

        Folder cChild1 = Folder.builder()
                .name("CChild1")
                .parent(cFolder)
                .build();

        Folder cChild2 = Folder.builder()
                .name("CChild2")
                .parent(cFolder)
                .build();

        Folder cChild3 = Folder.builder()
                .name("CChild3")
                .parent(cFolder)
                .build();

        Folder dFolder = Folder.builder()
                .name("D")
                .parent(aFolder)
                .build();

        Folder dChild1 = Folder.builder()
                .name("DChild1")
                .parent(dFolder)
                .build();

        Folder dChild2 = Folder.builder()
                .name("DChild2")
                .parent(dFolder)
                .build();

        Folder dChild3 = Folder.builder()
                .name("DChild3")
                .parent(dFolder)
                .build();

        folderRepository.saveAll(Arrays.asList(aFolder, bFolder, cFolder, dFolder, bChild1, bChild2,
                bChild3, cChild1, cChild2, cChild3, dChild1, dChild2, dChild3));
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("자가참조 엔티티 select 최적화 테스트")
    @Transactional()
    public void selectTest() throws Exception {

      /*  System.out.println(em.contains(aFolder));
        System.out.println(em.contains(bFolder));
        System.out.println(em.contains(cFolder));
        System.out.println(em.contains(dFolder));*/

        // when
        Folder folder = folderRepository.findById(1L).get();
        List<Folder> children = folder.getChildren();

        // then
        for (Folder child : children) {
            System.out.println("child_1dept = " + child);
            for (Folder grandChild : child.getChildren()) {
                System.out.println("grandChild = " + grandChild);
            }
        }
    }
    
    @Test
    @DisplayName("bfs 알고리즘 적용 테스트")
    @Transactional
    @Commit
    public void bfsTest() throws Exception {
    
        // given
        Queue<Folder> queue = new LinkedList<>();
        Folder deleteFolder = folderRepository.findById(1L).get();
        queue.offer(deleteFolder);

        List<Folder> tempFolders = new ArrayList<>();
        tempFolders.add(deleteFolder);

        // when
        while(!queue.isEmpty()) {

            Folder pollFolder = queue.poll();
            List<Folder> folders = pollFolder.getChildren();

            for (Folder folder : folders) {
                queue.offer(folder);
            }

            tempFolders.addAll(folders);
        }

        // then
        Collections.reverse(tempFolders);
        folderRepository.deleteAll(tempFolders);
        em.flush();
    }

}
