package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Child.ChildId> {
}
