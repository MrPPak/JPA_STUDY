package com.jpa.basic.test.embeddedId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Child2Repository extends JpaRepository<Child2, Child2.ChildId> {
}
