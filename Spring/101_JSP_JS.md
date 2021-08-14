#

## JSP
* Servlet 기반의 서버 스크립트 기술, HTML안에 Java 코드를 삽입할 수 있다.
* 실제로는 컴파일 시, HTML을 Java Servlet Class Code로 변경한다.
 그렇기 때문에 HTML에서 Java Code를 사용할 수 있는 것처럼 보이는 것이다.
* Example
    ```jsp
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
    String seq = request.getParameter("seq");
      
              // 2. DB 연동 처리
              BoardVO vo = new BoardVO();
              vo.setSeq(Integer.parseInt(seq));
      
              BoardDAO boardDAO = new BoardDAO();
              BoardVO board = boardDAO.getBoard(vo);
    %>  
    <html>
    <head>
        <title>Insert title here</title>
    </head>
    <body>
    
    </body>
    </html>
    ```
    * ```<% %>``` : Scriptlet 안에 정상적인 Java Code만 들어간다.
    * ```<%= %>``` : Expression 안에 변수와 메서드(Return Type이 void가 아닌)가 들어간다.
    * ```<!-- -->``` : HTML 주석, Compile 되지 않는다.


* session, request, response는 내장 객체이기 때문에 따로 선언하지 않고 
바로 사용이 가능하다.

## EL
* Expression Language
* JSP 파일에서 Request나 Response, Session에 등록
    * 등록된 값이 없다면, 무시한다.
    * 둘다 등록되어 있는 경우, Request가 Session보다 우선순위가 높다.
    * 대소문자를 구분한다. 에러가 날 수 있기 때문에 조심해야 된다.
* 사용 방법 : ${board.seq}, board라는 이름으로 등록된 객체의 seq 변수
    ```jsp
   
    ```


## JSTL
* JSP Standard Tag Library
* JSP 파일에서 if, for, switch 등과 같은 자바 코드를 대체하는 표준 태그
    ```jsp
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:if test="${user.role} == 'ADMIN'">
    	<a href='deleteBoard.do?seq=<%= board.getSeq() %>'>글삭제</a>&nbsp;&nbsp;&nbsp;
    </c:if>
    <c:forEach var="board" items="${boardList }">
    	<tr>
    		<td>${board.seq }</td>
    		<td align='left'><a href='getBoard.jsp?seq=<%= board.getSeq() %>'><%= board.getTitle() %></a></td>
    		<td>${board.writer }</td>
    		<td>${board.regDate }</td>
    		<td>${board.cnt }</td>
        </tr>
    <c:forEach>	
    ```






