package com.jpa.basic.test.transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SaveAllTest {

    @Autowired PostRepository postRepository;
    List<Post> posts;

    @BeforeEach
    public void setup() {
        posts = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            posts.add(new Post("test_post"));
        }
    }

    @Test
    public void saveAllTest() throws Exception {
        postRepository.saveAll(posts);
    }

    @Test
    public void saveTest() throws Exception {
        for (Post post : posts) {
            postRepository.save(post);
        }
    }
}
