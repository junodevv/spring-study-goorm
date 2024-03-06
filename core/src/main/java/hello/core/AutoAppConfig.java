package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @ComponentScan: 자동으로 스프링 빈을 끌어올린다?, 어노테이션들을 찾아들어가보면 @Component 가 붙어있는데 이런 애들을 모두 빈으로 자동 등록
// excludeFilters: bean 으로 자동 등록하지 않을 것들 지정,
    // 이전에 만든 AppConfig 등 에 @Configuration이걸 붙여놔서 그게 등록될수도 있어서 한거임
// basePackages: 어디서 부터 찾을지 경로지정, 탐색할 패키지의 시작위치
    // 모든 클래스(외부라이브러리 포함)를 다 찾아보기 때문에 범위를 정해주지않으면 굉장히 오래걸릴 수 있음

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
