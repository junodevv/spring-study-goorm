package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// 원래는 인터페이스와 구현체의 패키지를 분리하지만 지금은 예제니까 너무 복잡해지지 않게 같은 패키지에..
public class MemoryMemberRepository implements MemberRepository{
    // 실무에서는 동시성을 위해 concurrentHashMap을 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findMyId(Long memberId) {
        return store.get(memberId);
    }
}
