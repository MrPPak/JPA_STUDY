package com.jpa.basic.test.family_idclass;

import com.jpa.basic.test.family_idclass.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Child.ChildId> {
}
