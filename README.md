fishBaord
==========
## 깃 리파지토리
<img width="969" alt="2018-07-28 1 42 16" src="https://user-images.githubusercontent.com/37525926/43353155-1c5d7a5a-926c-11e8-9f89-33a89b867ed2.png">

## 메인 화면
<img width="957" alt="default" src="https://user-images.githubusercontent.com/37525926/44866086-2daf6300-acbf-11e8-8ae3-c314132c5a76.png">

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
 - 게시물 등록 후 목록 처리
    - WebREplyRepository와 연동해 실제 댓글 추가한다.
    - 댓글을 추가하는 동안 다른 사용자들이 댓글을 추가할 수 있는 가능성이 있다.
    - 이 문제를 해결하려 댓글을 추가한 후에는 데이터베이스에서 현재 게시물의 댓글을 새로 갱신하자.
    - addReply()는 WebRepository의 save()와 findBoard~()를 연속해 호출하므로 @Transaction영속성 처리를 해주자. 
 - 댓글 삭제
    - 댓글 삭제 후 다시 게시물을 갱신하도록 하자. 
    - 그러므로 댓글 삭제떄 rno가 필요하고,  갱신을 위해 게시물 번호가 필요하다.
 - 댓글 수정
    - PUT 방식으로 처리한다.
 - 화면에서 댓글 처리
    - 게시물 조회 화면에서 Ajax로 데이터를 처리하자. 
    - javaScript로 하나의 객체를 생성해 처리하는 모듈패턴을 이용해 보자.
 - 댓글 추가
    - 댓글 추가는 부트스트랩 Modal로 처리해보자.
 - 댓글 삭제
    - 댓글을 클릭해 Mdal을 보여주고 처리해보자.
 - 댓글 수정
    - 댓글 수정은 모든 내용을 JavaScript 객체로 구성하고 replyManager로 전달하자. PUT 방식으로..
 - 게시물 댓글 개수 
    - 게시물 리스트에 각 댓글 수 표시를 해보자.
    - 앞전에 게시물과 댓글을 양방향으로 설정했었다.
    - 양방향으로 잡고 list를 출력하려고 하면 화면에 출력되는 게시글의 수만큼 댓글 테이블을 조회하게 된다. 
    - 'N+1'의 상황이 발생했다. 
    - 이것이 발생한 이유는 게시물의 목록을 가져오는 쿼리가 단순히 tbl_webboads 테이블만 접근해서 처리하기 떄문이다. 
    - 그럼 어떻게 처리하나??
    - @Query를 이용해 직접 필요한 엔티티들 간의 관계를 설정해주면 된다. 
    - @Query는 고정된 JPQL을 실행하는덴 문제 없지만 동적으로 변하는 상황에선 어렵다(ex. 검색조건이 있다든지 처리..)
    - 그럼 어떻게 동적으로 만들까??
    
## 시큐리티 적용  
  - 회원과 회원에대한 권한이 있어야 하므로 domain 패키지에 Member, MemberRole을 구현하자.
  - 회원의 경우 스프링 시큐리티에서 username, password 단어를 사용하므로 충돌나지 않게 uname, upw로 지정하자.
  - Member와 MemberRole은 1:N, N:1 관계이고 MemberRole 자체가 단독생성되는 경우가 없으므로 단방향으로 하자.
  - 웹상 시큐리티는 필터 기반으로 동작한다. 
  - 특정 사용자 접근
    - SecurityConfig 클래스의 configure() 메서드를 이용해 보안을 적용하자.
  - 로그인 정보 삭제
    - 웹과 관련된 로그인 정보는 기본적으로 HttpSession을 이용한다. 
  - 커스텀 로그인 페이지 만들어보자
    - 기본적으로 시큐리티에서는 로그인 페이지를 제공한다.
    - 너무 단조로우므로... 수정하도록 하자!
    - formLogin()이후 loginPage() 메소드를 이용해 URI를 지정해 주면 된다. (SecurityConfig.java에서)
  - 로그아웃 처리
    - 스프링 시큐리티가 웹을 처리하는 방식의 기본은 HttpSession이므로 브라우저가 완전히 종료되면, 로그인한 정보를 잃게 된다. 
    - http.logout.invalidateHttpSession()으로 브라우저 종료하지 않고 정보 삭제할 수 있다. 
  - 리맴버 미
    - 쿠키의 값으로 암호화된 값을 전달한다. 키('fish')를 지정해 사용하자. 
    - 리멤버미를 데이터베이스에 보관하도록 설정하자. 
    - 다음 토큰보관 테이블 생성하자
     >
        mysql> create table persistent_logins(
               username varchar(64) not null,
               series varchar(64) primary key,
               token varchar(64) not null,
               last_user timestamp not null
               );
    - 시큐리티 적용된 수정 삭제 시 CSRF 파라미터가 반드시 필요하다. 
        - <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/> 요걸 추가하거나
        - th:action="@{/}" 를 form에 추가하면 된다. 
  - Ajax 시큐리티 처리
    - Ajax로 호출하는 작업에 CSRF 값이 같이 전송되야 한다. 
    - 댓글은 로그인한 사용자만 가능하도록 하게 하자.
    - 댓글컨트롤러에 해당 메서드에 @Secured(value = {"ROLE_USER","ROLE_ADMIN"}) 추가
  - 인터셉터를 추가하자
    - 인터셉터는 컨트롤러의 호출을 사전 or 사후에 가로챌 수 있따. 
    - 인터셉터는 서블릿 관련 자원들을 그대로 활용할 수 있따. 
    - default 메서드의 등장으로 WebMvcConfigurerAdapter는 디플리케이트 되었다. 
  - UserDetailsService 
    - 시큐리티가 제공하는 인터페이스
    - loadUserByUsername를 오버라이드 한다. 
        - 위에 @Transactional(readOnly = true)를 붙여야 레이지 로딩으로 불러올 수 있다. 
    - 빈으로 등록하는 방버은 컴포넌트 스캔으로 하면 된다. 
    - username으로 Member 엔티티 찾고 UserDtails를 구현하고 있는 객체에 Member의 id/pw를 저장한다. 
    - 사용자 권한 리스트도 생성해 UserDtails 객체에 설정한다. 
     

## ps
javascript 내장객체?
 - <label>등록일</label> <input class="form-control" name="regDate" th:value="${#dates.format(vo.regdate,'yyyy-MM-dd')}"
                                         readonly="readonly"/>
 - dates에서 앞에 # 안붙이면 500에러 난다...

html에서 자바스크립트 파일 불러오기
 - <script th:inline="javascript" th:src="@{'/js/reply.js'}"></script>
 
ajax 호출과정.
 - 클라이언트에서 ajax 요청하면 서버로 간다.
 - 서버는 XMLHttpRequest를 가지고 ajax요청을 처리한 후 결과를 브라우저에 전송. 
view.html
 >
                $("#delModalBtn").on("click",function () {
                   var obj = {bno: bno, rno: rno};
    
                   replyManager.remove(obj,function (list) {
                      printList(list);
                      alert("댓글이 삭제 되었습니다.");
                      $("#myModal").modal("hide");
                      replyTextObj.val("");
                      replyerObj.val("");
                   });
                }); 

reply.js
 >
        var remove = function (obj, callback) {
           console.log("remove...");
    
           $.ajax({
              type: 'delete',
              url: '/replies/'+obj.bno+"/"+obj.rno,
              dataType: 'json',
              contentType: "application/json",
              success:callback
           });
        };
        
### 조행기 게시판을 만들려한다.
1. JohangBoard 엔티티
  - id, title, content, files, regdate, editdate, user, location 칼람을 주기로하고..
  - Member와는 1:n 관계로 주고 단방향을 주기로하자.(게시물에서 회원을 가지게)
2. 사진 저장은 어떻게 해야하나??
  - DB 서버? (아마존?, 도커?)
  - 사진을 축소해야 하니 썸네일이란 것이 필요하겟다.
  - Location은 구글Map을 갖고오도록하자 (일단은 스트링으로 받고 나머지 기능 완성되면 구글맵을...)
 
## 찾아볼거
  - th:value / th:text 차이점...
  - Model과 RedirectAttributes 차이점...
  - 동적으로 쿼리 처리하는 방법...