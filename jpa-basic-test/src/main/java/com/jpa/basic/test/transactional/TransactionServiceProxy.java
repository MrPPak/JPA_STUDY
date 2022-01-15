package com.jpa.basic.test.transactional;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionServiceProxy extends TransactionService {

    private final EntityTransaction transaction;

    public TransactionServiceProxy(PostRepository postRepository, EntityManager em) {
        super(postRepository, em);
        this.transaction = em.getTransaction();
    }

    @Override
    public void runPersist(Post post) {
        super.runPersist(post);
    }

    @Override
    public void runRepository(Post post) {
        super.runRepository(post);
    }

    @Override
    public void transactionalPersistSave(Post post) {
        try {
            transaction.begin();
            super.transactionalPersistSave(post);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void transactionalRepositorySave(Post post) {
        try {
            transaction.begin();
            super.transactionalRepositorySave(post);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
