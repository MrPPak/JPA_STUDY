package com.jpa.basic.test.collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CollectionTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("collection 일대다 매핑 테스트")
    public void save() throws Exception {

        Member member = new Member();
        member.setUsername("member1");
        member.setHomeAddress(new Address("homeCity", "street", "10000"));

        member.getAddressHistory().add(new AddressEntity("old1", "street", "10000", member));
        member.getAddressHistory().add(new AddressEntity("old1", "street", "10000", member));

        memberRepository.save(member);
    }
}
