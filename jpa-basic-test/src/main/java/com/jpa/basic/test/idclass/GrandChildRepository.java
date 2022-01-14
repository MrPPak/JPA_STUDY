package com.jpa.basic.test.idclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GrandChildRepository extends JpaRepository<GrandChild, GrandChild.GrandChildId> {
}
