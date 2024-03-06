package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @ComponentScan: 자동으로 스프링 빈을 끌어올린다?, 어노테이션들을 찾아들어가보면 @Component 가 붙어있는데 이런 애들을 모두 빈으로 자동 등록
// excludeFilters: bean 으로 자동 등록하지 않을 것들 지정,
// 이전에 만든 AppConfig 등 에 @Configuration이걸 붙여놔서 그게 등록될수도 있어서 한거임

@Configuration
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
