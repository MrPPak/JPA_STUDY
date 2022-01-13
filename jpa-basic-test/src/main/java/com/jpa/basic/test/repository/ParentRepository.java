package com.jpa.basic.test.repository;

import com.jpa.basic.test.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
