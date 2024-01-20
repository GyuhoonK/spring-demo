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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // App의 설정 정보(구성 정보)를 담고 있다
public class AppConfig {
    /*
    - 실제 구현체의 선택은 AppConfig(공연기획자)에 의해 결정된다
    - 이러한 방식을 생성자를 통해서 주입(injection)한다고 말한다
    - 객체의 생성과 연결을 담당한다
    - 클라이언트(memberServiceImpl) 입장에서는 의존 관계를 외부에서 주입된 것으로 보이기 때문에 DI(의존관계 주입)이라고 부른다
    - AppConfig의 역할: 구성 영역
     */
    @Bean // method에 Bean을 추가하면, 스프링 컨테이너에 등록된다 name 변경하려면 @Bean(name="mmm")
    public MemberRepository memberRepository() { // 인터페이스를 반환해주는 method
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 비율 할인 정책으로 수정
    }
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // cmd + opt + m (리팩토링)

    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


}
