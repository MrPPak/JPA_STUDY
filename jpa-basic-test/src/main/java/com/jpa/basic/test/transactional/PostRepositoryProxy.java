package com.jpa.basic.test.transactional;

import org.springframework.data.repository.core.EntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PostRepositoryProxy extends PostRepositoryClass {

    private EntityTransaction transaction;

    public PostRepositoryProxy(EntityManager em, EntityInformation entityInformation) {
        super(em, entityInformation);
        this.transaction = em.getTransaction();
    }

    @Override
    public Post save(Post post) {
        try {
            transaction.begin();
            Post savePost = super.save(post);
            transaction.commit();
            return savePost;

        } catch (Exception e) {
           transaction.rollback();
           throw new RuntimeException();
        }
    }

    @Override
    public List<Post> saveAll(List<Post> posts) {
        try {
            transaction.begin();
            List<Post> savePosts = super.saveAll(posts);
            transaction.commit();
            return savePosts;

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
    }
}
