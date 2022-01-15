package com.jpa.basic.test.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

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

    // @Override
    @Transactional
    public List<Post> saveAll(List<Post> posts) {
        Assert.notNull(posts, "Entities must not be null!");

        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            result.add(save(post));
        }

        return result;
    }
}
