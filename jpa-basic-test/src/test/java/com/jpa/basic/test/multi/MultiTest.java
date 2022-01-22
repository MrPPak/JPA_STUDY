package com.jpa.basic.test.multi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;

@SpringBootTest
public class MultiTest {

    @Autowired MultiParentRepository multiParentRepository;
    @Autowired MultiChildRepository multiChildRepository;
    @Autowired EntityManager em;


    @Test
    @Transactional
    public void find() throws Exception {
        MultiParent parent = new MultiParent(2L, "parentItem");
        multiParentRepository.save(parent);

        MultiChild child1 = new MultiChild("childItem1", parent);
        MultiChild child2 = new MultiChild("childItem2", parent);
        multiChildRepository.saveAll(Arrays.asList(child1, child2));

        em.flush();
        em.clear();

        MultiParent findParent = multiParentRepository.findById(1L).get();
        System.out.println(findParent.getChildren());
    }
}
