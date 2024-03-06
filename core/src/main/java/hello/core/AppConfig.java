package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 정보란 뜻의 어노테이션
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 이렇게되면 싱글톤패턴이 꺠지는 것 아닌가 ->

    // 생성자를 통해서 객체가 들어감 == 생성자 주입 == DI,Dependency Injection == 의존관계 주입
    // MemoryMemberRepository를 갖고 있는 MemberServiceImpl객체 반환
    @Bean // 스프링 컨테이너 라는 곳에 등록되는 아이들
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 아래처럼 작성하면 정책이 바뀌어도 아래에서만 바꿔주면되고 역할과 구현 클래스를 한 눈에 볼 수 있게 된다.
    // 멤버 리포지토리는 메모리멤버레포를 쓸것이다.(역할을 한눈에 볼 수 있도록 리팩토링한 코드)
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
/*스프링 적용 전 코드*/
/*
// 생성자를 통해서 객체가 들어감 == 생성자 주입 == DI,Dependency Injection == 의존관계 주입
// MemoryMemberRepository를 갖고 있는 MemberServiceImpl객체 반환
public MemberService memberService(){
    return new MemberServiceImpl(memberRepository());
}
public OrderService orderService(){
    return new OrderServiceImpl(memberRepository(), discountPolicy());
}

//아래처럼 작성하면 정책이 바뀌어도 아래에서만 바꿔주면되고
// 역할과 구현 클래스를 한 눈에 볼 수 있게 된다.

// 멤버 리포지토리는 메모리멤버레포를 쓸것이다.(역할을 한눈에 볼 수 있도록 리팩토링한 코드)
public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
}
// 할인정책은 fix할인정책을 쓸 것이다.
public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
    return new RateDiscountPolicy();
}
*/
