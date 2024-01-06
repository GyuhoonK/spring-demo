package hello.demo.member;

import hello.demo.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
//    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach // test 실행 전에 반드시 실행함
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given: 주어지는 값
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when : 행동
        memberService.join(member);
        Member findMember = memberService.findeMember(1L);
        // then : 기대 결과
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
