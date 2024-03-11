package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 구현체가 하나인 경우 클래스명 끝에 "Impl"을 붙이는 관례가 있다.
@Component
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class), 스프링 Bean 컨테이너에서 알아서 파라미터에 주입해준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findMyId(memberId);
    }

    //Test용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
