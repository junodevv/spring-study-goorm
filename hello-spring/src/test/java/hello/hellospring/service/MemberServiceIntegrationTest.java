package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 변경하상을 DB에 커밋을 하지 않고 롤백을 해주는 어노테이션
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입(){
        //given : 값 주기
        Member member = new Member();
        member.setName("hello");

        //when : ~ 동작할떄
        Long saveId = memberService.join(member);

        //then : 검증하기
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //예외처리 테스트
    @Test
    void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 람다식 속의 로직을 실행했을떄, 해당exception이 터져야한다.
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> memberService.join(member2)
        );

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}