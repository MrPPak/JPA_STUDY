package com.jpa.basic.test.inheritance;

import java.util.Optional;

public interface ItemRepositoryCustom {

    Optional<Item> findByIdCustom(Long id);
}
