package kr.co.fastcampus.cli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws ClassNotFoundException {
		logger.info("Hello world!");

//		Connection connection = null;	// Connection 생성
//		Statement statement = null;
		Class dirver = Class.forName("org.h2.Driver");
		String url = "jdbc:h2:mem:test;MODE=DB2";
//		connection = DriverManager.getConnection(url, "sa","");	// Connection은 DirverManager.getConnection을 통해서
//		statement = connection.createStatement();	// DB에서 SQL을 호출할 수 있도록 Statement를 정의

		try(Connection connection  = DriverManager.getConnection(url, "sa","");
						Statement statement = connection.createStatement()){
			// Transaction, connection.commit() / connection.rollback() 사용
			connection.setAutoCommit(false);	// commit이 진행되야 DB에 저장이 진행된다.

			statement.execute("create table member(id int auto_increment, username varchar(255) not null, password varchar(255) not null, primary key(id))");

			try {
				statement.executeUpdate("insert into member(username, password) values('yoon','1234')");
				connection.commit();
			}catch(SQLException e){
				connection.rollback();
			}
			ResultSet resultSet = statement.executeQuery("select id, username, password from member");
			while(resultSet.next()){
				Member member = new Member(resultSet);
				logger.info(member.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}