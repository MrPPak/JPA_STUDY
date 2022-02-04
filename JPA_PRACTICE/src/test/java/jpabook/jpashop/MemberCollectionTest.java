package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
public class MemberCollectionTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void 하이버네이트_내장컬렉션_확인() {
        Member member = new Member();
        System.out.println("getOrders = " + member.getOrders().getClass());

        em.persist(member);
        System.out.println("getOrders = " + member.getOrders().getClass());
    }


}
