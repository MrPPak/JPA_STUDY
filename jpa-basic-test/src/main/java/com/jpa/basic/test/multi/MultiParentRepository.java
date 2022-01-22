package com.jpa.basic.test.multi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiParentRepository extends JpaRepository<MultiParent, Long> {
}
