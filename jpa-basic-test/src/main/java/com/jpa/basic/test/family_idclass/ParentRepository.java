package com.jpa.basic.test.family_idclass;

import com.jpa.basic.test.family_idclass.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
