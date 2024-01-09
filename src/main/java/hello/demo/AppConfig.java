package hello.demo;

import hello.demo.discount.DiscountPolicy;
import hello.demo.discount.FixDiscountPolicy;
import hello.demo.discount.RateDiscountPolicy;
import hello.demo.member.MemberRepository;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import hello.demo.member.MemoryMemberRepository;
import hello.demo.order.OrderService;
import hello.demo.order.OrderServiceImpl;

public class AppConfig {
    /*
    - 실제 구현체의 선택은 AppConfig(공연기획자)에 의해 결정된다
    - 이러한 방식을 생성자를 통해서 주입(injection)한다고 말한다
    - 객체의 생성과 연결을 담당한다
    - 클라이언트(memberServiceImpl) 입장에서는 의존 관계를 외부에서 주입된 것으로 보이기 때문에 DI(의존관계 주입)이라고 부른다
    - AppConfig의 역할: 구성 영역
     */
    private MemberRepository memberRepository() { // 인터페이스를 반환해주는 method
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 비율 할인 정책으로 수정
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // cmd + opt + m (리팩토링)

    }
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


}
