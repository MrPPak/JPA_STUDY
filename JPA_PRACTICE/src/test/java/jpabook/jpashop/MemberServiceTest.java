package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("park");

        // when
        Long savedId = memberService.join(member);

        // then
        assertThat(savedId).isNotNull();
    }

    @Test
    void 중복회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("park");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("park");

        // when, then
        assertThatThrownBy(() ->
                memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }
}
