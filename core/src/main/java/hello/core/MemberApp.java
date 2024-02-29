package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        /*스프링 적용*/
        // 스프링이 AppConfig 내부의 @Bean들을 applicationContext속에 넣어 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // getBean(메서드명, 반환타입(인터페이스))
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        /**
         * commend + option + v == 변수명, 자료형 자동완성
         * soutv == 위의 변수를 출력하는 sout문을 자동생성해줌
         * */
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
