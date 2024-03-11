package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 필드(필수값들)를 가지고 생성자를 만든다. 생성자 주입할떄 사용하는 생성자를 똑같이 만들어줌
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findMyId(memberId);
        // 아래코드가 단일책임원칙을 잘 지킨것, 할인에대해 변경이 필요해지면 할인쪽만 바꾸면됨
        int discountPrice = discountPolicy.discount(member, itemPrice); // 난 모르겠고, 이 member에 이 가격인데 할인가격은 얼마야?

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // Test 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

/*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    */
//위 두 코드 할인정책의 변경을 위해서 클라이언트인 현재 클래스를 변경해야하는 문제 발생, DIP, OCP위반

/** DIP, OCP 지키는 코드
 * 여기서(클라이언트)는 인터페이스에만 의존하고, 어떤 할인정책, DB를 사용하는지는 AppConfig(생성자를 호출하는쪽에서 결정)
 * */
//    <필드 주입 방식> == 비추
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;

//    <수정자 주입 방식(setter주입)>
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//    <생성자 주입방식>, field에 final 붙여줘야함
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }