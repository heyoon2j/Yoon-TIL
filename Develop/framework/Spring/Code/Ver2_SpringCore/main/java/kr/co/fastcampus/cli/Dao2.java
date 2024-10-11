package kr.co.fastcampus.cli;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class Dao2 {

    private Connection connection;

    public Dao2(Connection connection){
        this.connection = connection;
    }

    public void run(){

        try(Statement statement = connection.createStatement()){
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
                log.info(member.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
