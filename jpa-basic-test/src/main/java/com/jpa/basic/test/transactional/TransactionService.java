package com.jpa.basic.test.transactional;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final PostRepository postRepository;
    private final EntityManager em;

    public void runPersist(Post post) {
        transactionalPersistSave(post);
    }

    public void runRepository(Post post) {
        transactionalRepositorySave(post);
    }

    @Transactional
    public void transactionalPersistSave(Post post) {
        em.persist(post);
        System.out.println("영속화 여부: " + em.contains(post));
    }

    @Transactional
    public void transactionalRepositorySave(Post post) {
        postRepository.save(post);
        System.out.println("영속화 여부: " + em.contains(post));
    }
}
