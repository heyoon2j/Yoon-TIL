# DAO & VO
* Database

## DAO
* Data Access Object, Database의 데이터에 접근하는 트랜잭션 객체
* DB 연결부터 데이터 조회, 삭제 등의 DB 접근에 대한 기능들을 가지고 있는 객체이다.
* DAO를 사용하는 이유는 효율적인 Connection 관리와 보안성 때문이다.
    * Connection 관리: 일반적으로 Connection Pool을 이용하여 관리한다. 미리 Pool에 Connection 객체를 생성해 두고, 필요 시 Pool에 있는 Connection 객체를 바로 사용하게 된다.
     하지만 서비스 상에서는 많은 Connection이 발생하기 때문에 미리 생성된 Connection도 금방 다 사용하게 된다. 이렇게 되면 Connection Pool에서도 기존과 마찬가지로 Dirver 로드, Connection 객체 생성을 반복하게 된며 많은 오베헤드가 발생하게 된다..
     이를 방지하고 효율적으로 사용하기 위해 DB에 접근하는 객체를 전용으로 하나만 만들어서 사용하는 방법으로 나온 것이 DAO다.
    * 보안성

### DAO Code
```java
// DAO(Data Access Object) 클래스
public class BoardDAO {
    // JDBC 관련 변수 선언
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    // Board 테이블 관련 SQL 명령어
    private static final String BOARD_INSERT = "insert into board(seq, title, writer, content) "
            + "values((select nvl(max(seq), 0) + 1 from board), ?, ?, ?);";
    private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?;";
    private static final String BOARD_UPDATE_CNT = "UPDATE BOARD SET CNT = CNT + 1 WHERE SEQ = ?;";
    private static final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?;";
    private static final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?;";
    private static final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY SEQ DESC;";

    // BOARD 테이블 관련 CRUD 기능의 메서드
    // 글 등록
    public void insertBoard(BoardVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
}
```
* DAO 구조 구성 시 보면 좋은 자료: https://www.oracle.com/java/technologies/dataaccessobject.html
    * inteface를 이용한다.


### DB Connection Code
```java
public class JDBCUtil {

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (SQLException e) {

        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }

        try {
            if(stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            if(conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
```
* 해당 Code는 Connection Pool을 사용하지 않은 Code이다.

## VO
* Value Object
* DTO(Data Transfer Object)라고도 한다.
* VO(DTO)는 계층간 데이터 교환을 위해 사용되는 객체이다. Database의 데이터와 매핑하기 위한 객체이기 때문에 
보통 Getter/Setter Logic 만을 가지고 있다.

### VO Code
```java
// Lombok Annotation Getter/Setter
@Getter
@Setter
public class BoardVO {
    // 테이블의 컬럼 이름(데이터 타입까지)과 동일한 멤버변수를 private로 선언한다.
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;
    private String searchCondition;
    private String searchKeyword;
}
```



