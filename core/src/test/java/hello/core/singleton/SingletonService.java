package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    /**
     * 이렇게 static으로 쓰면 클래스영역에 올라가기 때문에 딱 하나만 존재하게 된다. 자기 스스로 객체를 생성해서 instance 안에 넣어둔다.
     */
    private static final SingletonService instance = new SingletonService();

    // public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
