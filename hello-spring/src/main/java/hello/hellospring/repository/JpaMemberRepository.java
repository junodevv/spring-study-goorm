package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

//@Repository
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa 는 "EntityManager" 를 통해서 작동한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // primarykey는 쿼리없이 find 메서드로 찾기 가능
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // 일반 속성들은 쿼리(JPQL)를 통해서 찾아야함
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class) // JPQL: 객체를 대상으로 쿼리를 날리는 메소드, as 는 생략가능
                .getResultList();
    }
}
