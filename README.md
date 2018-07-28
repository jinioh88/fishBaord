fishBaord
==========
## 깃 리파지토리
<img width="969" alt="2018-07-28 1 42 16" src="https://user-images.githubusercontent.com/37525926/43353155-1c5d7a5a-926c-11e8-9f89-33a89b867ed2.png">

# 추가 설정
----------
## mysql 추가
1. 데이터 베이스 생성 
 mysql> create database fish_board;
 Query OK, 1 row affected (0.00 sec)

2. application.properties 내용 추가
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/board_ex?useSSL=false
   spring.datasource.username=root
   spring.datasource.password=

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.generate-ddl=true
   spring.jpa.show-sql=true
   spring.jpa.database=mysql
   spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect

   spring.thymeleaf.cache=false

   logging.level.org.hibernate=info

3. pom.xml 추가
 <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
 </dependency>

## 레이아웃 추가
1. resources/templates/layout 폴더밑에 레이아웃 파일들을 저장하자.

2. 부트 스트랩 추가.

## 타임리프
1. th:with="지역변수명 = '값''"
 - 지역변수를 작성.
 - 특정 범위에서만 유효한 지역변수를 선언할 떄 사용.
 >     
     <div class="panel-body">
          <p>[[${result}]]</p>
          <div th:with="result=${result.result}">
              <ul class="list-group">
                  <li class="list-group-item" th:each="board:${result.content}">[[${board}]]}</li>
              </ul>
          </div>
      </div>
      
2. 하단 숫자 표시
 > 
        <nav>
             <div>
                 <ul class="pagination">
                     <li th:each="p:${result.pageList}">
                     <a href="#">[[${p.pageNumber}+1]]</a></li>
                 </ul>
             </div>
        </nav>

3. '이전', '다음' 페이지는  th:if로 검사해준다.

4. 검색조건
  - '/boards/list?type=t&keyword=5' 로 파라미터가 왔을때 컨트롤러에서 처리해준다.
  - @ModelAttribute로 받아서 처리한다. 
  - 위 주소로 request 보내면 @ModelAttribute로 담은 객체의 생성자가 실행된다.
  - $("#searchBtn").click(function(e) : 버튼 id가 searchBtn으로 주엇졋을떄 javascript 처리 부분
  - formObj.find("[name='page']").val("1"); // 폼에서 name='page'가 있는 부분 찾아서 값을 1로 세팅


## 페이지 처리
1. @PageableDefault로 처리하는 경우도 있지만 보안상 노출이 된다.

2. 그래서  Value Object를 생성하는 방식으로 작성한다. 

3. QuerydslPredicateExecutor<>를 상속 받아 검색 조건 및 페이징 처리를 한다. 

4. vo.PageMaker에서 페이지 숫자 표시를 처리.

## 게시물 쓰기 처리
1. 'GET' 방식으론 입력 화면 보고 ,'POST' 방식으론 게시물 등록 처리한다.
2. controller 패키지 및에 FishBoardController에서 처리를 한다.
3. board/register.html
 >    <div class="panel-body">
           <form th:action="@{register}" method="post">   // post 방식으로 요청을 보낸다. /register라는 url로
               <div class="form-group">
                   <label>제목</label> <input class="form-control" name="title" th:value="${vo.title}"/>
                   <p class="help-block">제목을 입력하세요.</p>  // input에 기본적인 값이 들어옮
               </div>
           </form>
      </div>
      
4. RedirectAttributes 
  - RedirectAttributes의 addFlashAttribute()를 이용하면 URL상 전달되는 값이 보이지 않는다.
  - 실질적으로 데이터가 전달 되기 떄문에 글이 등록 되엇다는 점을 표현할 수 있따.       
        
-----

## 게시물 조회
1. 게시물 조회는 다름 경우를 고려하자.
  - 검색 조건이 없는경우 조회 : 페이지 번호 유지
  - 검색 조건이 있는 경우 조회 : 페이지 번호 + 검색 조건 유지
  - <form>태그로 사용자가 다시 원래의 리스트 화면으로 이동할 수 있는 링크를 제공하자.
  - <td><a th:href='${board.bno}' class='boardLink'>[[${board.title}]]</a></td>
  - 위에서 class='boardLink'는 javascript에서 사용하려고 쓴 것이다.
  - javascript 부분에서 $(".boardLink").click(function(e)){...})
>  $(".boardLink").click(function (e) {
                 e.preventDefault();
                 var baordNo = $(this).attr("href"); 
                 formObj.attr("action",[[@{'/boards/view'}]]);
                 formObj.append("<input type='hidden' name = 'bno' value='"+boardNo+"'>");
                 formObj.submit();
              });
              
2. 조회 링크
 - 조회 페이지는 내용을 보는 용도로, 수정/삭제는 별도의 페이지로 구분하자.
 - 다음 두 링크가 필요하다.
 - 수정/삭제 페이지 링크  |  게시물 리스트로 이동하는 링크
 - 조회 페이지는 검색조건+페이징조건 값이 유지 된채로 이동 될수 있게 하자.
 > <a th:href="@{modify(page=${pageVO.page}, size=${pageVO.size},type=${pageVO.type},keyword=${pageVO.keyword},
           bno=${vo.bno})}" class="btn btn-default">수정하기/글삭제</a>
 - 위 Thymeleaf 링크처리는 키=값 형태로 파라미터를 연결해 링크를 생성한다.
 - 위는 <a href="modify?page=1&size=10&type=&keyword=&bno=20" .. 으로 처리된다.
----

## 게시물 삭제 수정
1. 게시물 수정/삭제는 다음 순서로 처리하자
    - 게시물 수정/삭제 페이지 처리
    - 삭제 및 이동
    - 수정 및 이동
 
2. 컨트롤러에 수정/삭제 페이지 등록

3. 삭제 처리 
    - 수정과 삭제는 POST 방식으로 처리한다. 
    - 컨트롤러 POST 처리 등록 
    - addAttribute()는 addFlashAttribute()와 달리 url에 추가되어 전송된다.
 
4. 수정처리
    - 수정처리는 삭제 처리와 비슷허다.
    - 컨트롤러에서 게시글 조회 후 수정한 다음 repository에 save 하자.
    
## 댓글처리
 - REST 방식으로 댓글을 처리해 보도록 하자.
 - JavaScript를 이용해 Ajax 호출을 하는 방식을 사용해 보도록 하자. 
 - Ajax로 호출하면 댓글 관련 내용이 특정 게시물 조회 화면에서 이뤄지게 된다. 
 - JPA 설계와 Repository의 설계/테스트
    - 게시글과 댓글의 관계는 '일대다', '다대일' 관계이다. 
    - 양방향으로 설계를 해보도록 한다. 
    - domain 패키지에 WebReply 클래스를 만든다.
 - 연관관계 설정
    - FishBoard와 WebReply는 양방향 관계로 설정할 것이므로 WebReply에 FishBoard를 @ManyToOne 관계로 설정
    - FIshBoard에는 @OneToMany 관계를 설정하자.
    - 지연로딩으로 불필요하게 양쪽 테이블 조회 되지 않게 하자. 
 - @JsonIgnore 어노테이션
    - REST 방식에서 데이터를 전송하거나 반환할때 JSON 형태 데이터 주고 받게 설계한다.
    - SPring MVC는 객체 데이터를 자동으로 JSON 데이터로 처리해주는 'jackson-databind'를 이용해 JSON 변환을 처리한다. 
    - 문제는 양방향일 경우 변환이 상호호출되 무한히 반복해 생성하는 문제가 생길 수 있다. 
    - 특정 속성에 @JsonIgnore를 붙여 JSON으로 안변하게 하면 된다.
 - ReplyRepository 추가
    - WebReply는 단독으로 CRUD가 가능하니 별도로 리파지토리를 만들자.
 - ReplyController를 추가
    - REST 방식에서 가장 중요한 결정은 각 작업을 위한 URL을 설계하는 것이다.
    - REST 방식에서 자원은 보통 복수형을 사용한다. 고로 '/replies'의 형태로 잡아주자.
    - 댓글 추가 : POST방식. /replies/게시물번호
    - 댓글 삭제 : DELTE방식. /replies/게시물번호/댓글번호
    - 댓글 수정 : PUT방식. /replies/게시물 번호
    - 댓글 보기 : GET방식. /replies/게시물 번호
 - 게시물 댓글 등록
    - JSON 형태로 처리하기로 했는데 @PathVariable과 @RequestBody 에노테이션이 필요하다. 
    - @PathVariable : URI일부를 파라미터로 받기위함.
    - @RequestBody : JSON 형태로 전달되는 데이터를 객체로 자동 변환하기 위함.
    - addReply()의 리턴값이 ResponseEntity 타입인데 이 코드를 이용해 HttpResponse의 상태 코드를 처리할 수 있다. 
    - 
    
## ps
javascript 내장객체?
 - <label>등록일</label> <input class="form-control" name="regDate" th:value="${#dates.format(vo.regdate,'yyyy-MM-dd')}"
                                         readonly="readonly"/>
 - dates에서 앞에 # 안붙이면 500에러 난다...
 
 
## 찾아볼거
  - th:value / th:text 차이점...
  - Model과 RedirectAttributes 차이점...