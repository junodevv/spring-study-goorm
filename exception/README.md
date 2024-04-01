# 스프링 MVC 2편 - 예외처리 부분(섹션 8,9)

## 서블릿 예외 처리
1. 서블릿 예외 처리 방식 두가지
   1. 자바직접실행 exception
   2. response.sendError(http 상태코드, 메시지)
2. 오류 화면 제공(`WebServerCustomizer`)
3. 오류페이지 작동 원리 - WAS에서 보낸 request에서 에러 속성들 꺼내봄
4. 필터
5. 인터셉터

## 스프링 부트

1. 스프링 부트가 제공하는 에러페이지
   - `404.html`, `4xx.html`, `500.html`
   - `BasicErrorController`가 제공하는 기본정보들

## API 예외 처리
1. 클라이언트의 Accept와 controller의 produces를 이용한 에러응답
2. HandlerExceptionResolver 시작 - `IllegalArgumentException`를 잡아서 정상응답하기
3. HandlerExceptionResolver 활용 - 직접 ExceptionResolver를 작성
4. 스프링이 제공하는 ExceptionResolver1 - ResponseStatusExceptionResolver(http응답코드변경) => 어노테이션(`@ResponseStatus`), 인스턴스`ResponseStatusException`
5. 스프링이 제공하는 ExceptionResolver2 - DefaultHandlerExceptionResolver(스프링 내부 예외 처리)
6. `@exceptionHandler`