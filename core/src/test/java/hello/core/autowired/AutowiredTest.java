package hello.core.autowired;

import hello.core.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean { // 스프링 컨테이너에서 관리되는 Member 빈은 없음
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){  // 호출 자체가 안됨
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ // 호출은 되지만 Null이 반환됨
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3){ // 호출은 되지만 Optional.empty 가 반환됨
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
