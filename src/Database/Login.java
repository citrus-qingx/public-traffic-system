package Database;

import javax.swing.*;
import java.sql.*;

/**
 * @author serum
 * @create 2022/1/2 18:41
 */
public class Login {
    //  MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3308/publictrafficsystem" +
            "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF8";

    // 数据库的用户名与密码
    static String USER;
    static String PASS;

    // 连接
    public Connection conn = null;
    public Statement stmt = null;

    public Login(String USER,String PASS) throws SQLException,ClassNotFoundException{
        this.USER = USER;
        this.PASS = PASS;
        Class.forName(JDBC_DRIVER);
        // 连接数据库
        System.out.println("正在连接数据库...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("连接成功");

    }

    public ResultSet searchMysql(String sql) throws SQLException {// 查询
            System.out.println("正在查询...");
            stmt = conn.createStatement();
            // 结果存在rs返回
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
    }

    public void closeSql(ResultSet rs){
         try{
             //            关闭资源
             rs.close();
             stmt.close();
             conn.close();
         }catch(Exception se){
             // 处理 JDBC 错误
             se.printStackTrace();
         }// 处理 Class.forName 错误
         finally{
             // 关闭资源
             try{
                 if(stmt!=null) stmt.close();
             }catch(SQLException ignored){}
             try{
                 if(conn!=null) conn.close();
             }catch(SQLException se){
                 se.printStackTrace();
             }
         }
        System.out.println("Goodbye!");
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    }
}