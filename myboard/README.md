# 게시판 구현하기 과제

## API 요구사항
1. 게시글 등록
   - [x] 단건의 게시글을 등록 할 수 있어야한다.
2. 게시글 삭제
   - [ ] 단건의 게시글을 삭제할 수 있어야한다.
     - [x] 게시글의 삭제방법은 sofe delete로 진행
     - [ ] 게시글 삭제시 댓글도 삭제되어야 한다.
3. 게시글 수정
   - [x] 단건의 게시글을 수정 할 수 있어야 한다.
     - [x] 삭제된 게시글은 수정 할 수 없어야 한다.
4. 게시글 목록 조회
   - [x] 등록된 게시글의 목록을 조회한다.
     - [x] 응답에는 본문이 포함되지 않는다.
   - [x] 페이징이 가능해야 한다.
     - [x] 페이징의 방식은 offset기반 페이징과 cursor기반 페이징 중 택 1 (구현방식의 차이점 학습, 비교, 선택) 
        => offset 선택, 이유: 간단한구현과 여러페이지를 넘어갈 수 있기 때문, 또한 클라이언트와 소통하는 방식이라니라 cursor에 어려움이있을것이라 생각함
     - [x] 정렬 순서는 최신글이 우선순위가 가장 높도록 한다.
5. 댓글 등록
   - [ ] 게시글에 댓글을 등록한다.
6. 댓글 수정
   - [ ] 게시글에 댓글을 수정한다.
7. 댓글 삭제
   - [ ] 게시글에 댓글을 삭제한다.
   - [ ] 삭제는 soft delete로 진행한다.
8. 게시글 단 건 조회
   - [ ] 게시글의 제목과 본문 모두 응답에 포함되어야한다.
   - [ ] 해당 게시글에 등록된 댓글 리스트도 응답에 포함되어야한다.
     - [ ] 댓글 중 삭제된 댓글은 포함하지 않는다.
     - [x] 댓글리스트 페이징은 무시

## BD ERD 작성
<img src="https://github.com/junodevv/spring-study-goorm/assets/126752196/e1fe1c55-46a8-4070-9db2-61861c5d7dc0">

## 배운것들
1. soft delete
2. pagination -> Pageable(interface)-PageRequest(class)
3. EntityDtoMapper
4. JPA Update ->  @Modifying, @Transaction
5. @ModelAttribute
6. @RequestParam과 @PathVariable의 차이
7. DB 저장시 기본값 설정하기
    - 스프링이 생성자로 사용하는거 1순위가 파라메터가 없는 기본생성자,
      그 기본생성자에 기본값을 정해놓을 수 있다.
    - 스프링은 기본생성자로 객체를 생성하고 거기에 setter를 이용해서 클라이언트에서 받아온 파라메터들을 세팅한다.
8. 연관관계
  - 객체(JPA)와 테이블(DB)의 연관관계를 맺는 차이
    - 하지만 객체(JPA)는 **2개의 단방향 연관관계**로 양방향 연관관계를 표현한다.
    - 테이블(DB)은 **1개의 양방향 연관관계**로 양방향 연관관계를 표현한다.
      - 외래키 하나로 두 양쪽으로 조인할 수 있기떄문
  - 이때, JPA에선 테이블에 참조하는 두개의 객체 필드가 생기게 되는데 어느 필드 사용했을때 DB를 관리할 것인가에 대한 딜레마가 생기게됨
  - 그래서, **객체의 양방향 연관관계는 관리의 주인이 필요**
    - 연관관계의 주인만이 외래키를 관리, 주인이 아닌쪽은 읽기만 가능
    - 주인은 `mappdeBy`속성 사용 X, 주인이 아니면 `mappedBy`속성으로 주인 지정
  - 연관관계의 주인은 외래키가 있는 곳, 1:N일때 N쪽
9. `cascade`(영속성 전이) 
   - 특정 엔티티를 영속상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만드는 옵션
   - 엔티티를 영속화 할때 연관된 엔티티도 함꼐 영속화 하는 편리함 제공
     - ex, 부모엔티티를 저장할 때 자식 엔티티도 함께 저장 하는 경우 
     - 주의, 연관관계 매핑과는 관련이 없다. 
   - 하나의 부모`Entity`가 자식들을 관리할 때 사용하기 좋다. => 소유자가 하나일때
     - 자식들을 관리하는게 하나의 부모가 아닌 여러 부모라면 쓰지 않는게 좋다.
   - 종류
     - **ALL: 모두 적용**
     - **PERSIST: 영속(저장할때만)**
     - REMOVE: 삭제
     - MERGE: 병합
     - REFRESH: REFRESH 
     - DETACH: DETACH 
10. `fetch = FetchType.LAZY` => JPA는 기본적으로 지연로딩을 하는데 때로는 불러올 엔티티와 연관된 다른 엔티티를 함께 로드해야할 경우가 있다. 이때 사용하는 것
  - 지연로딩: 엔터티가 실제로 필요할 때까지 데이터베이스에서 로드해오지 않는 것
    