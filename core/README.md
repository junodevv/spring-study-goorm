# 스프링 핵심 원리 - 기본편

---
1. DIP, OCP를 지키지 못하는 코드
2. DIP, OCP를 만족하는 순수 자바코드(AppConfig)
3. 스프링을 적용한 코드(AppConfig)
   - 스프링 컨테이너를 생성, 스프링 빈 등록, 의존관계 설정
4. 스프링 빈 조회
   - 전체조회, 타입﹒이름 조회
   - 동일 한 타입이 둘 이상
   - **상속 관계** - 부모타입을 조회하면 자식까지 모두 조회가능
5. 다양한 설정방식 -> xml을 이용한 AppConfig설정
6. Bean메타정보(BeanDefinition)
7. Singleton
   - @Configuration이 싱글톤을 어떻게 지키나
8. 의존관계 자동 주입
   - @ComponentScan, @Conponent, @Autowired
   - **생성자 주입**, 수정자 주입, 필드 주입, 메서드 주입
   - Null처리 -> 생성자 주입이 답이다!
   - **Lombok!!**
   - 같은 타입의 빈이 2개 이상일 경우
     - Autowired 필드명 매칭, @Qualifier, @Primary
     - 애노테이션 직접 만들어쓰기(커스텀)
   - 모든 빈 조회(List, Map)
   - 자동주입=업무 로직 수동주입=기술 지원 로직
9. Bean 생명주기, 콜백
   1. 인터페이스 InitializingBean, DisposableBean(옛날방식)
   2. Configuration에서 Bean생성, 종료 메서드 등록
   3. 애노테이션 @PostConstruct, @PreDestroy 사용(스프링 권장)
10. Bean 스코프
    - Singleton VS Prototype
    - Singleton 안에서 Prototype을 사용할때 생기는 문제와 해결
      1. @ObjectProvider, @ObjectFactory -> Lookup한다
      2. JSR330 Provider를 쓴다
    - Request 스코프(웹 스코프)
      1. 스프링에 의해 DI가 일어날때, Http 요청이 없는데 request스코프의 빈 생성과 주입이 일어나 에러 발생
         - 해결방법 1: Provider 
         - 해결방법 2: Proxy 사용