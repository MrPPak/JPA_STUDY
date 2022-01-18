package com.jpa.basic.test.inheritance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private QItem item = QItem.item;
    private JPAQueryFactory queryFactory;

    @Autowired
    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Item> findByIdCustom(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(item).where(item.id.eq(id)).fetchOne());
    }
}
