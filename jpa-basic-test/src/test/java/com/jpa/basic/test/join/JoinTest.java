package com.jpa.basic.test.join;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JoinTest {

    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired EntityManager em;

    @Test
    @Transactional
    @DisplayName("findAll N+1 문제 테스트")
    public void findAll() throws Exception {

        // given
        Team teamA = new Team("A");
        Team teamB = new Team("B");
        teamRepository.saveAll(Arrays.asList(teamA, teamB));

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 30, teamB);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        System.out.println(em.contains(teamA));
        System.out.println(em.contains(member1));

        em.flush();
        em.clear();

        // when
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("teamName = " + member.getTeam().getName());
        }
    }


}
