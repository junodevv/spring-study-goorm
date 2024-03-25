# 응답/에러(API)모델 만들기

---
## API 요구사항
1. 이름과 성적을 입력받아 저장
   - [ ] 성적의 입력은 특정값(`6`)이 넘을 경우 에러응답이 나타나야한다.


2. 입력된 성적을 조회하는 API
   - [ ] 성적 오름차순으로 조회가 되어야 한다.


3. 특정 성적을 입력받아, 해당 성적의 학생만 조회하는 API

## 구현 요구사항
1. Contorller 에서 응답 모델로 만들어 주어야 한다.
   - [ ] `ApiResponse<T>`: 여러가지 데이터 타입(클래스)를 result로 넣을 수 있도록 제네릭을 사용하여 구현한다.
   - [ ] `makeResponse(T result), makeResponse(List<T> results)`: 결과를 응답 객체로 만들어주기 위한 메서드, 
     - 단건과 복수건 결과 모두 응답 객체로 만들어 줄 수 있도록 두개 모두 구현한다.


2. 에러 응답을 만들기 위해서 `@ExceptionHandler` 를 사용하여 exception의 데이터를 이용해야한다.


3. exceptionHandler에서 응답 모델을 만들때 필요한 데이터가 포함시킬 수 있는 customException을 구현한다.
   - [ ] ex) CustomException(ErrorCode, message, data)  
     - [ ] ErrorCode는 enum 으로 정의한다.
   