package com.team3.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {                                                
        public static Connection getConnection() throws Exception {                                        
                // Mysql DB 연결 시                                
                String url = "jdbc:mysql://203.250.32.44:3306/team0";   // team0 은 각 조 DB명                                
                String driver = "com.mysql.jdbc.Driver";                                
                // 오라클 DB 연결 시                                
                // String url = "jdbc:oracle:thin:@localhost:1521:xe";                                
                // String driver = "oracle.jdbc.driver.OracleDriver";                                
                                                
                String user = "team0"; // java                                
                String pw = "team0!"; // java                                
                                                
                // 드라이버 로딩(jar 파일 로드)                                
                Class.forName(driver);                                
                Connection conn = DriverManager.getConnection(url, user, pw);                                
                return conn;                                
        }                                        
}                                                