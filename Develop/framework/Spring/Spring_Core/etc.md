* 
   ```xml
   <error-page>
      <error-code>
          <error-></error->
      </error-code>
   </error-page>
   ``` 
  
```java
@RequestMapping()
String getBoardList(BoardVO vo, BoardDAOJDBC boardDAO, Model mav) {
    // 절대 결과는 세션에 저장해서는 안된다. 검색 결과는 Requset에 등록해야 한다.
    // ModelAndView나 Mode 객체에 검색 결과를 등록하면 자동으로 Request에 등록해준다.
    mav.addObject("boardList", boardDAO.getBoardList(vo));  // Model 정보
    mav.addObject("search", vo);    // Model 정보
    return "getBoardList"; // Model은 Req에 등록되므로, View는 String으로 전달한다.
}
```

* SQL문으로 써야되는 경우
    1) 코드로 구현하기 어렵지만 SQL문으로 처리하면 간편한 경우
    2) 
    3) 

```
// 글 목록 검색
public String getBoardList(BoardVO vo, BoardDAOJDBC boardDAO, Model model) {
    if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE);
    // 절대 검색 결과는 세션에 저장해서는 안된다. 검색결과는 request에 등록해야 하낟.
    // Model 객체에 검색 결과를 등록하면 세션이 아닌 Request에 등록해준다.
    model.addAttribute();
    mode.addAttribute();
    return "getBoardLIst";

}


// @SessionAttributes를 이용하여 특정 이름으로 Model에 저장한 데이터를 세션에도 등록되도록 한다.
// 따라서 getBoard 메서드에서 Model에 "board"라는 이름으로 BoardVO 객체를 Model에 저장하면
// 세션에도 "board"라는 이름으로 BoardVO 객체가 등록되는 것이다.
// 여러개 배열형태로 등록 가능 @SessionAttributes({"board", "abc"}) 
@SessionAttributes("board")
@Controller
public class BoardController {
    // @ModelAttributes는 세션 "board"라는 이름으로 데이터가 등록되어있다면 그 객체를 vo 변수에 바인딩해라 라는 의미
    public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAOJDBC boardDAO) {
        boardDAO.update(vo);
        return "foward:getBoardList";
    }
}
// https://offbyone.tistory.com/333
```

* Controller는 DAO를 직접 건드리면 안된다.
    * DAO를 건드리면 다 수정해야한다.
    * AOP가 적용되지 않는다. 이유는 ServiceImpl에 적용하고 있기 때문에
    ```java
    public class BoardController {
      @Autowired
      private BoardService boardService;
      public String {
          boardService.get
      }
    }
    ```
  
```xml

CharacterEncodingFilter도 Spring에서 제공하는 Filter가 있다.



```  


/////////////////////////////////
// META-INF의 persistence.xml에 설정한다.
```xml
<persistence>
    <persistence-unit name="JPAProject">
        <properties> 
       		<!-- 필수 속성 -->
       		<property name="javax.persistence.jdbc.driver" value="org.h2.Driver" />
       		<property name="javax.persistence.jdbc.user" value="sa" />
       		<property name="javax.persistence.jdbc.password" value="" />
       		<property name="javax.persistence.jdbc.url"	value="jdbc:h2:tcp://localhost/~/test" />
       		<!-- -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
        
       		<!-- 옵션 -->
        	<property name="hibernate.show_sql" value="true" />
        	<property name="hibernate.format_sql" value="true" />
        	<property name="hibernate.use_sql_comments" value="false" />
        	<property name="hibernate.id.new_generator_mappings" value="true" />
        	<property name="hibernate.hbm2ddl.auto" value="create" />
            <!-- create, Application을 만들때마다 Table, Sequence를 Drop하고 새로 만들어라 -->
            <!-- update, 있다면 업데이트, 없으면 만든다. -->
        </properties>
    </persistence-unit>
</persistence>
```

```java
import javax.persistence

@Data
@Entity
@Table(name="NEW_BOARD", 
       uniqueConstraints={@UniqueConstraint(columnNames={"SEQ", "WRITER"})}) 
// unique 제약조건
public class Board {

    @Id // Primary Key를 의미
    @GeneratedValue // AutoIncrement 의미
    //@GeneratedValue(strategy=), Default는 AUTO
    // Oracle 같이 시퀀스를 지원하면 sequence를 사용
    // Table은 따로 Key를 가지는 테이블을 만든다.
    // Auto로 하면 DB에 맞게 자동으로 설정해준다. Oracle은 SEQUENCE, MySQL이면 IDENTITY
    // 아니면 Table로 설정하면
	private Long seq;

    // nullable=false는 not null
    // Length Default = 255
	@Column(nullable=false, length=200)
	private String title;

    // Update할 때 update하지 마라는 의미
    // 
	@Column(nullable=false, updatable=false)
	private String writer;

	@Column(nullable=false)
	private String content;

    // TIMESTAMP:
    // Temporal: java.util.Date type의 날짜 데이터를 매핑할때 사용
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="date default sysdate")
//    @Column(name="regDate") // 원하는 이름으로 Colum 설정, 써주지 않으면 기본 변수명으로 설정
	private Date createDate = new Date();

	@Column(columnDefinition="number default 0")
	private Long cnt = 0L;
	
	@Transient
	private String searchCondition;

	@Transient
	private String searchKeyword;
}



}

public class BoardServiceClient {
	public static void main(String[] args) {
		// EntityManager 생성
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("테스터");
			board.setContent("JPA 내용........");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			// 글 등록 처리
            // transaction 시작
            tx.begin();
			em.persist(board);
            // tx 종료
			tx.commit()
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}

  ```

New Entity : 비영속 상태, @Entity이 붙은 VO 객체를 생성만 한것
MANAGED : 영속 상태, VO 객체를 Persistence Context에 등록
REMOVED : 삭제 상태, 
DETACHED : 

