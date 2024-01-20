package hello.demo;

import hello.demo.member.Grade;
import hello.demo.member.Member;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class MemberApp {
    public static void main(String[] args) { // psvm + enter

//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // Spring Container 사용 //
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // AppConfig의 구성 정보를 활용하겠다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // AppConfig의 memberService에 해당하는 method를 찾아온다, 반환 타입은 MemberService.class

        Member member = new Member(1L, "memberA", Grade.VIP); // long type이므로 1L으로 입력
        memberService.join(member);

        Member findMember = memberService.findeMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
