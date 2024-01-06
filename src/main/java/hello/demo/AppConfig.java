package hello.demo;

import hello.demo.discount.FixDiscountPolicy;
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
     */
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());

    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
