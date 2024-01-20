package hello.demo.binfind;

import hello.demo.AppConfig;
import hello.demo.member.MemberService;

import hello.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        @Test
        @DisplayName("빈 이름으로 조회")
        void findBeanByName() {
            MemberService memberService = ac.getBean("memberService", MemberService.class);
//            System.out.println("memberService = " + memberService);
//            System.out.println("memberService.getClass() = " + memberService.getClass());

            assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

        }
        @Test
        @DisplayName("이름 없이 타입으로만 조회")
        void findBeanByType() {
            MemberService memberService = ac.getBean(MemberService.class);
//            System.out.println("memberService = " + memberService);
//            System.out.println("memberService.getClass() = " + memberService.getClass());

            assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
//            System.out.println("memberService = " + memberService);
//            System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByX() {
//        MemberService memberService = ac.getBean("XXXXX", MemberServiceImpl.class);
//            System.out.println("memberService = " + memberService);
//            System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XXXXX", MemberServiceImpl.class)); // lambda


    }

}
