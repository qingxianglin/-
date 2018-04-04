package com.sun.swingset3.sql;

import java.sql.*;

/**
 * 数据库工具类父类
 */
public class DBUtilBase {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/parkinglot_manager?characterEncoding=utf8";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "12345";

    public DBUtilBase() {
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement getStatement(Connection conn, String sql){
        try{
            return conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        }catch (Exception e){
            return null;
        }
    }

    public void cleanUp(ResultSet rs,Connection conn, Statement stmt) {
        try {
            if(rs!=null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

