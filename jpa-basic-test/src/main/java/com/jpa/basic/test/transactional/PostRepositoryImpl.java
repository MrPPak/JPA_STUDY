package com.jpa.basic.test.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class PostRepositoryImpl {

    private final EntityManager em;
    private final EntityInformation entityInformation;

    // @Override
    @Transactional
    public Post save(Post post) {
        if(entityInformation.isNew(post)) {
            em.persist(post);
            return post;
        } else {
            return em.merge(post);
        }
    }
}
