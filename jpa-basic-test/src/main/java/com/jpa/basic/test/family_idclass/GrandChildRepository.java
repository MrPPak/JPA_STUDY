package com.jpa.basic.test.family_idclass;

import com.jpa.basic.test.family_idclass.GrandChild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrandChildRepository extends JpaRepository<GrandChild, GrandChild.GrandChildId> {
}
