fishBaord
==========
# 추가 설정
----------
## mysql 추가
1. 데이터 베이스 생성 
 >mysql> create database fish_board;
 >Query OK, 1 row affected (0.00 sec)

2. application.properties 내용 추가
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/fish_board?useSSL=false
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

4. Querydsl 설정
    pom.xml에 추가 하자. 
            <dependency>
    			<groupId>com.querydsl</groupId>
    			<artifactId>querydsl-apt</artifactId>
    			<scope>provided</scope>
    		</dependency>
    
    		<dependency>
    			<groupId>com.querydsl</groupId>
    			<artifactId>querydsl-jpa</artifactId>
    		</dependency>
    		

## 레이아웃 추가
1. resources/templates/layout 폴더밑에 레이아웃 파일들을 저장하자.

2. 부트 스트랩 추가.

3. layout 작성

## 컨트롤러 작성
1. FishBoardController.java 작성

## 도메인 작성
1. FishBoard.java 생성

## Querydsl 설정
1. pom.xml 설정

2. Qdomain 생성
  - pom.xml에 추가
  --------------------
    <plugin>
                  <groupId>com.mysema.maven</groupId>
                  <artifactId>apt-maven-plugin</artifactId>
                  <version>1.1.3</version>
                  <executions>
                      <execution>
                          <goals>
                              <goal>process</goal>
                          </goals>
                          <configuration>
                              <outputDirectory>target/generated-sources/java</outputDirectory>
                              <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
                          </configuration>
                      </execution>
                  </executions>
    </plugin>
    
    
## 테스트 코드 작성
1. 게시글 더미 데이터 삽입 테스트
 - @Test
   public void insertBoards()