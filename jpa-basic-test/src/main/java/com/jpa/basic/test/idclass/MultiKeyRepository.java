package com.jpa.basic.test.idclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiKeyRepository extends JpaRepository<MultiKey, MultiKey.MultiKeyId> {
}
