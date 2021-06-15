# Database & JDBC

# Database
* 통합하여 관리되는 데이터의 집합체를 의미
* DB의 종류는 SQL DB, NoSQL DB이 있다.
1. **SQL DB**
    * **MySQL**
    * **PostgreSQL**
2. **NoSQL DB**
    * **MongoDB**
    * **Cassandra**

## SQL vs NoSQL 특징
1. **SQL DB**
    * 데이터를 Row, Column 형태로 저장
    * Schema가 고정되어 있다(데이터 무결성 보장)
    * DB의 하드웨어 성능을 높이는 방법으로 수직적으로 확장(Scale UP/Down)
        * 일반적으로 SQL DB는 한 대의 Server가 전체를 처리
        * 물론 트래픽 분산을 위해 수평적 확장을 한다(읽기 전용 복제본)
    * 선택해야 하는 경우
        1) 엄격한 Schema 규칙 및 데이터 품질 적용이 필요
        2) ACID 규정 준수가 필요
            * ACID는 DB 트랙젝션이 안정적으로 처리되도록 보장하는 4가지 핵심 속성인 원자성, 일관성, 고립성, 지속성을 말한다.
            > Transaction : DB의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위 또는 한꺼번에 모두 수행되어야 할 일련의 연산들을 의미
        3) DB가 과도한 Read/Write 용량을 필요로 하지 않는 경우
        4) 데이터 세트를 결합하고 연결해야 하는 경우
        
2. **NoSQL DB**
    * 데이터를 Key-Value 형태, 문서 및 그래프 등을 비롯한 다양한 스토리지 모델 중 하나를 사용하여 저장 
    * Schema가 동적이다. 
    * DB Server를 추가하는 방법으로 수평적 확장을 한다(Scale Out/In)
        * NoSQL DB는 분산 처리 시스템, Name Node가 Query를 받아 Data Node들에게 Query를 던진다. 이러면 Data Node들이 동시에 처리하여 스펙이 적어도 빠르다. 
        * 물론 RDMS도 두 대 이상의 고성능 서버에 Storage로 Cluster를 구성하면 빠르다.
    * 선택해야 하는 경우
        1) DB를 수평적으로 확장해야 하는 경우
        2) 데이터가 기존 Schema에 적합하지 않은 경우
        3) Read/Write 속도가 기존 SQL DB에서 경제적으로 지원할 수 있는 범위를 초과하는 경우
        4) 복잡한 연결이(JOIN) 필요하지 않은 경우
        5) 대규모 Read/Write 속도가 필요한 경우


## H2
* 여기서는 H2 사용
* https://www.h2database.com/html/main.html
* 좋은점
    * File Mode 지원 : 파일로 DB를 저장할 수 있다(JDBC URL: jdbc:h2:{File Path})
    * Memory Mode 지원(In-Memory) : 휘발성으로 Test 하기 좋다(JDBC URL: jdbc:h2:mem:{File Path})
    * 호환 Mode 지원 : **H2 Hompage -> Feature -> Compatibility**에서 확인 가능

### Install
1. Download All Platforms
2. Execute H2
    * https://stackoverflow.com/questions/58979455/h2-database-created-in-java-not-found-in-h2-console


### Maven 연동 방벙
1. Go to "Maven Central" (https://search.maven.org/)
2. Search "H2-database"
3. Copy Maven "com.h2database:h2"
4. Paste into the Maven


# JDBC
* JAVA DataBase Connection
* Java에서 제공하는 DBMS와 연동을 위해 제공되는 자바 표준 API(java.sql)이다.

## JDBC Driver
* java.sql 패키지의 Interface를 구현한 클래스를 Driver라고 한다.
* JDBC Interface를 통해서 Driver의 기능들을 사용하기 때문에 Driver의 코드들을 숙지할 필요가 없다.
* Driver는 DB Vendor 마다 JDBC Interface에 맞춰 코드를 개발하게 된다.
* API는 Java Docs에서 확인 가능 

## DB 기본 연결 순서
1. Driver Loading
2. Create Connection
3. Create Statement
4. Transfer SQL
5. ResultSet(SELECT인 경우)
6. Close Resource
* Example
```java
public class dbConnection{
    public static void main(String[] args){
        // 1. 드라이버 객체를 메모리에 로딩
        Class.forName("org.h2.Driver");
        // DriverManager.registerDriver(new org.h2.Driver());       

        // DB 연결 정보 URL 생성
        String url = "jdbc:h2:mem:test;MODE=MySQL;";
    
        // 2. Connection 생성, DriverManager.getConnection 이용하여 Connection을 획득
        // 3. DB에 SQL문을 호출하기 위한 Statement 생성, SQL 전달 객체 Statement 생성
        try(Connection connection  = DriverManager.getConnection(url, "sa",""); Statement statement = connection.createStatement()){
            // getConnection()를 호출하게 되면 주어진 입력 값으로 Connection을 설립한다.

            // 4. SQL 전달
		    // Transaction을 위한 Method, connection.commit() / connection.rollback() 사용
            // commit이 진행되야 DB에 저장이 진행된다. 에러가 발생하며 rollback을 해야되기 때문에 false로 지정
			connection.setAutoCommit(false);

			statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");
			try {
				statement.executeUpdate("insert into member(username, password) values('yoon','1234')");
			}catch(SQLException e){
                // Update 시, 에러가 발생하면 Rollback 시킨다.
				connection.rollback();
			}
    
            // 5. SQL에 대한 값을 가지고 온다.
            // ResultSet은 Statement 당 하나의 객체만 Open할 수 있다. 그렇기 때문에 추가 ResultSet이 필요한 경우, Statement를 생성해야 한다.
            // ResultSet은 Statement가 close되면 자동으로 닫힌다.
            // https://docs.oracle.com/javase/6/docs/api/java/sql/Statement.html#close()
			ResultSet resultSet = statement.executeQuery("select id, username, password from member");
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				Member member = new Member(id, username, password);

				logger.info(member.toString());
			}
            // Update가 정상적으로 되었으므로, DB Commit
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
```

 