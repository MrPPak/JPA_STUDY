package com.jpa.basic.test.transactional;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.persistence.EntityManager;

public class TransactionServiceProxy extends TransactionService {

    private final PlatformTransactionManager transactionManager;

    public TransactionServiceProxy(PostRepository postRepository, EntityManager em) {
        super(postRepository, em);
        this.transactionManager = new DataSourceTransactionManager();
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
        // getTransaction()을 하면 트랜잭션이 시작된다.
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            super.transactionalPersistSave(post);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    @Override
    public void transactionalRepositorySave(Post post) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            super.transactionalRepositorySave(post);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }
}
