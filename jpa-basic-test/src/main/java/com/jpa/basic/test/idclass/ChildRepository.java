package com.jpa.basic.test.idclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Child.ChildId> {
}
