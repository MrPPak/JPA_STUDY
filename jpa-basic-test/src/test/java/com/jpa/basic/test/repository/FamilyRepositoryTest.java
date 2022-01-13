package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Child;
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

    @Test
    @DisplayName("insert Test")
    public void insertTest() throws Exception {

        Parent parent = new Parent("부모");
        parentRepository.save(parent);

        Child child = new Child(parent);
        childRepository.save(child);
    }
}
