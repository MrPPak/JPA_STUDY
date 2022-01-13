package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Child;
import com.jpa.basic.test.domain.GrandChild;
import com.jpa.basic.test.domain.Parent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FamilyRepositoryTest {

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    GrandChildRepository grandChildRepository;

    @Test
    @DisplayName("insert Test")
    public void insertTest() throws Exception {

        Parent parent = new Parent("부모");
        Parent saveParent = parentRepository.save(parent);

        Child child = Child.builder()
                .name("자식")
                .parent(saveParent)
                .build();
        Child saveChild = childRepository.save(child);

        GrandChild grandChild = GrandChild.builder()
                .name("손자")
                .child(saveChild)
                .build();
        grandChildRepository.save(grandChild);
    }
}
