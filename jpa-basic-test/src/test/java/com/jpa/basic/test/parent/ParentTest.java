package com.jpa.basic.test.parent;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ParentTest {
    @Autowired
    EntityManager em;

    @Autowired
    Parent3Repository parentRepository;

    @Autowired
    Child3Repository childRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void DB와_객체_동일성_테스트(){
        Child3 child1 = new Child3("자식1");

        Parent3 parent1 = new Parent3("부모1");
        Parent3 parent2 = new Parent3("부모2");

        parentRepository.saveAll(Arrays.asList(parent1, parent2));
        parent1.addChild(child1);
        em.flush();
        em.clear();

        Parent3 findParent1 = parentRepository.findById(parent1.getId()).get();
        Parent3 findParent2 = parentRepository.findById(parent2.getId()).get();

        Child3 findChildren = findParent1.getChildren().get(0);
        findChildren.changeParent(findParent2);
        // em.flush(); // SQL 적용 내용을 1차 캐시에 적용한다. -> parent2의 getChildren()에 child1을 넣어준다.

        assertThat(findParent1.getChildren()).hasSize(0);
        assertThat(findParent2.getChildren()).hasSize(1);
    }
}
