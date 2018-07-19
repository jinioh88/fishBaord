fishBaord
==========
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
  

## 페이지 처리
1. @PageableDefault로 처리하는 경우도 있지만 보안상 노출이 된다.

2. 그래서  Value Object를 생성하는 방식으로 작성한다. 

3. QuerydslPredicateExecutor<>를 상속 받아 검색 조건 및 페이징 처리를 한다. 

4. vo.PageMaker에서 페이지 숫자 표시를 처리.
