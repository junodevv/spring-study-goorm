package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    // 생성자를 통해서 객체가 들어감 == 생성자 주입 == DI,Dependency Injection == 의존관계 주입
    // MemoryMemberRepository를 갖고 있는 MemberServiceImpl객체 반환
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    /**
     * 아래처럼 작성하면 정책이 바뀌어도 아래에서만 바꿔주면되고
     * 역할과 구현 클래스를 한 눈에 볼 수 있게 된다.
     * */
    // 멤버 리포지토리는 메모리멤버레포를 쓸것이다.(역할을 한눈에 볼 수 있도록 리팩토링한 코드)
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 할인정책은 fix할인정책을 쓸 것이다.
    private DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
