package com.jpa.basic.test.multi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiChildRepository extends JpaRepository<MultiChild, Long> {
}
