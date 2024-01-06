package hello.demo.order;


import hello.demo.discount.DiscountPolicy;
import hello.demo.discount.RateDiscountPolicy;
import hello.demo.member.Member;
import hello.demo.member.MemberRepository;
import hello.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정율할인정책으로 변경
    /* 문제점
    - 클라이언트인 OrderServiceImpl의 코드를 수정해야한다
    - OCP, DIP 객체지향 설계 원칙을 충실히 준수했는가? => 그렇게 보이지만 아니다
    [ DIP 위반]
    - 클래스 의존 관계를 분석해보면, 추상(인터페이스) 뿐만 아니라, 구체(구현)클래스에 의존하고 있다
      - 추상(인터페이스) 의존: DiscountPolicy
      - 구현(클래스) 의존: FixDiscountPoilcy, RateDiscountPolicy
    - 코드를 수정할 때마다, 구현(클래스)도 참고해야한다 => DIP(의존관계)에 위배된다, 인터페이스에만 의존하지 못하고 있다
    [ OCP 위반 ]
    - OrderServiceImpl 소스코드도 수정해야한다 => OCP 위반
    [ 해결책 ]
    - 구현(클래스) 의존을 제거하고, 인터페이스에만 의존하도록 수정하자
     */

    /*
     [ 해결책1 ] => 인터페이스에만 의존하기
    private DiscountPolicy discountPolicy;
    구현체를 참조하지 못하고 있는데 코드를 어떻게 실행하지? => NPE 발생한다
    뭔가 이상한데? 어떻게 DIP를 지킬 수 있는거지??
    */

    /*
    [ 해결책2 ] AppConfig
    - 누군가 클라이언트(OrderServiceImpl)에 DiscountPolicy 객체를 생성하는 역할을 담당해야한다
    - AppConfig의 등장
    - Impl은 인터페이스에 대한 의존성만을 갖는다(DIP 준수)
     */

    // 어떤 구현체(인스턴스)가 주입될지는 Impl 입장에서는 알 수 없다
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
