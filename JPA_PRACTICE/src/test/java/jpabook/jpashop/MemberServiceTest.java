package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test
    public void 회원가입() {
        // given
        Member member1 = new Member();
        member1.setName("park");

        // when
        Long saveId = service.join(member1);

        // then
        Assertions.assertThat(member1.getId()).isEqualTo(service.findOne(saveId).getId());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원예외(){
        // given
        Member member1 = new Member();
        member1.setName("park");

        Member member2 = new Member();
        member2.setName("park");

        // when
        service.join(member1);
        service.join(member1);

        // then
        Assertions.fail("예외가 발생해야 합니다.");

    }

}
