package kr.co.fastcampus.cli;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionFactory {
    private  Connection connection;
    private String driverClass;
    private String url;
    private String user;
    private String password;

    public ConnectionFactory(String driverClass, String url, String user, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection createConnection() throws SQLException {
//        String url = "jdbc:h2:mem:test;MODE=DB2";
        try {
            Class.forName(this.driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(this.url, this.user,this.password);
    }

    private void init() throws SQLException {
        log.info("init");
        this.connection = createConnection();
    }

    private void destroy() throws SQLException {
        log.info("destroy");
        if(this.connection != null)
            this.connection.close();
    }

}
