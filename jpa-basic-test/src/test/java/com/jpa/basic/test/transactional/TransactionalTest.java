package com.jpa.basic.test.transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class TransactionalTest {

    @Autowired TransactionService transactionService;
    @Autowired ApplicationContext applicationContext;


    @Test
    @DisplayName("과연 transactionService 객체는 프록시인가")
    public void proxyTest() throws Exception {

        String className = applicationContext.getBean(transactionService.getClass()).getClass().getName();
        System.out.println("className = " + className);
    }

    @Test
    @DisplayName("transactionalRepositorySave 테스트")
    public void transactionalSaveTest() throws Exception {

        Post post = new Post("test_post");
        transactionService.transactionalRepositorySave(post);
    }

    @Test
    @DisplayName("runPersist 메소드를 호출해도 트랜잭션을 탈 것인가")
    public void runPersistTest() throws Exception {

        Post post = new Post("test_post");
        transactionService.runPersist(post);
    }

    @Test
    @DisplayName("runRepository 메소드를 호출해도 트랜잭션을 탈 것인가")
    public void runRepositoryTest() throws Exception {

        Post post = new Post("test_post");
        transactionService.runRepository(post);
    }

}
