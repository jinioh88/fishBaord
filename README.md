fishBaord
==========
1. 추가 설정
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


