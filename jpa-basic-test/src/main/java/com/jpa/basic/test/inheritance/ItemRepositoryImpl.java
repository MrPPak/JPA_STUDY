package com.jpa.basic.test.inheritance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ItemRepositoryImpl implements ItemRepositoryCustom{

<<<<<<< Updated upstream
    // private QItem item = QItem.item;
=======
    //private QItem item = QItem.item;
>>>>>>> Stashed changes
    private JPAQueryFactory queryFactory;

    @Autowired
    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Item> findByIdCustom(Long id) {
<<<<<<< Updated upstream
       // return Optional.ofNullable(queryFactory.selectFrom(item).where(item.id.eq(id)).fetchOne());
=======
>>>>>>> Stashed changes
        return null;
    }
}
