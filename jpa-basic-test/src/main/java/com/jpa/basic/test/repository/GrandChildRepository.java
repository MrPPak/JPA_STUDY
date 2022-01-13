package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.GrandChild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrandChildRepository extends JpaRepository<GrandChild, GrandChild.GrandChildId> {
}
