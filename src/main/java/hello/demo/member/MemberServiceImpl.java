package hello.demo.member;

public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // 실제 구현 객체를 선택함, OCP 스럽지 못하다
    // 실제 구현(impl)과 추상화 모두에 의존하고 있음

    /* 해결책
        구현체를 선언하지 않고 생성자로 대체한다
     */
    private final MemberRepository memberRepository; // 인터페이스만 존재함, 추상화에만 의존 (DIP를 지킨다)

    // 생성자
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findeMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
