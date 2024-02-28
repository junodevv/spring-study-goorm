package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override

    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findMyId(memberId);
        // 아래코드가 단일책임원칙을 잘 지킨것, 할인에대해 변경이 필요해지면 할인쪽만 바꾸면됨
        int discountPrice = discountPolicy.discount(member, itemPrice); // 난 모르겠고, 이 member에 이 가격인데 할인가격은 얼마야?

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
