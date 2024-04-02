# 응답/에러(API)모델 만들기

---
## 과제하면서 배운 내용들

1. 에러 응답
    1. `@RestControllerAdvice` 사용
    2. `@ExceptionHandler` 사용
2. Exception을 커스텀 하는 것

3. `@JsonInclude(Include.NON_EMPTY)`

> Json응답시 정상응답과 에러응답에서 포함되어야 하는 값이 달랐는데 포함되지 않는 값이 null 로 표시되는 문제 발생 
> 
> ⇒ 위 어노테이션을 사용하여 필요하지 않은 값(값이 null인 값)은 Json응답시에 포함되지 않도록할 수 있었다.


4. `@Getter` 를 사용한 직렬화 역직렬화
> 직렬화: `자바 객체` → `Jackson의 ObjectMapper` →`JSON`
>
> 역직렬화: `JSON` → `Jackson의 ObjectMapper` → `자바 객체`
>
> 직렬화 대상 :
> 
> 1)`public`필드,
> 
> 2)`Getter`, `Setter` 를 이용해 유추한 필드
> 
> `Getter`: 역직렬화, 직렬화 모두 가능 / `Setter`: 역직렬화만 가능
> 
> https://joon2974.tistory.com/25


---
## API 요구사항
1. 이름과 성적을 입력받아 저장
   - [x] 성적의 입력은 특정값(`6`)이 넘을 경우 에러응답이 나타나야한다.


2. 입력된 성적을 조회하는 API
   - [x] 성적 오름차순으로 조회가 되어야 한다.


3. 특정 성적을 입력받아, 해당 성적의 학생만 조회하는 API

---
## 구현 요구사항
1. Contorller 에서 응답 모델로 만들어 주어야 한다.
   - [x] `ApiResponse<T>`: 여러가지 데이터 타입(클래스)를 result로 넣을 수 있도록 제네릭을 사용하여 구현한다.
   - [x] `makeResponse(T result), makeResponse(List<T> results)`: 결과를 응답 객체로 만들어주기 위한 메서드, 
     - 단건과 복수건 결과 모두 응답 객체로 만들어 줄 수 있도록 두개 모두 구현한다.


2. 에러 응답을 만들기 위해서 `@ExceptionHandler` 를 사용하여 exception의 데이터를 이용해야한다.


3. exceptionHandler에서 응답 모델을 만들때 필요한 데이터가 포함시킬 수 있는 customException을 구현한다.
   - [x] ex) CustomException(ErrorCode, message, data)  
     - [x] ErrorCode는 enum 으로 정의한다.

---

응답모델 예시

<img src="https://github.com/junodevv/spring-study-goorm/assets/126752196/acba9934-d88c-4b2b-a6dd-cfa18d08bcc8">

에러모델 예시

<img src="https://github.com/junodevv/spring-study-goorm/assets/126752196/2555763f-b0c5-4799-9ad6-45201b78434c">

- status.code: http status가 아니라 서어베서 정의하는 code값을 담는다.(정상응답: 2000)
- status.message: 정상응답시 "OK", 에러응답시 에러에 대한 상세한 이유를 담는다.
- metadata.resultCount: 정상응답시 나타나는 값, result list의 갯수를 담아준다.
- results: 정상응답시 나타나는 값, 항상 list형태, 실제 응답으로 내주고 싶은 정보가 표시
- data: 에러응답시 나타나는 값, front에서 사용자에게 어떤 이유로 요청이 거부되었는지 메시지를 만들기 쉽게 필요한 데이터를 넣어준다.

