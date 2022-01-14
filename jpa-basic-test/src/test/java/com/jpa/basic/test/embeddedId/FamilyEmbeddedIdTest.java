package com.jpa.basic.test.embeddedId;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FamilyEmbeddedIdTest {

    @Autowired
    Parent2Repository parentRepository;

    @Autowired
    Child2Repository childRepository;

    @Test
    @DisplayName("insert Test")
    public void insertTest() throws Exception {

        Parent2 parent = new Parent2("부모");
        Parent2 saveParent = parentRepository.save(parent);

        Child2.ChildId childId = new Child2.ChildId();
        childId.setParentId(saveParent.getId());

        Child2 child = Child2.builder()
                .name("자식")
                .parent(saveParent)
                .childId(childId)
                .build();
        childRepository.save(child);
    }
}
