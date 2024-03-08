package hello.hellospring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //jpa 가 관리하는 entity가 된다.
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 부여하는 전략을 사용하고 있다는 의미(IDENTITY)
    private Long id; // 임의의 값, 시스템 저장을 위한 값

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
