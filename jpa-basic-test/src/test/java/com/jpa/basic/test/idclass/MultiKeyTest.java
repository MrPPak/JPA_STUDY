package com.jpa.basic.test.idclass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MultiKeyTest {

    @Autowired
    MultiKeyRepository multiKeyRepository;

    @Test
    @DisplayName("multikey save Test")
    public void saveTest() throws Exception {

        MultiKey multiKey = new MultiKey("테스트 멀티키1");
        multiKeyRepository.save(multiKey);
    }
}
