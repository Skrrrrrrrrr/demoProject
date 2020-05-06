/**
 * 数据库连接层MYSQL
 * @author Administrator
 *
 */
import java.sql.*;

public class DBConnection {

    /**
     * 连接数据库
     * @return
     */
    public static Connection getDBConnection()
    {
        // 1. 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 获取数据库的连接
        try {
            Connection conn  = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", "root", "root");
            // Connection conn  = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF-8", "root", "root");
            return conn;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }
    
}





/////////////////////////////////////////////////////////////////////////////
//DB基本操作
// class MySQL{
//     // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//     static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//     static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
    
//     // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//     //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
//     //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";
    
    
//     // 数据库的用户名与密码，需要根据自己的设置
//     static final String USER = "root";
//     static final String PASS = "123456";
    
//     public static void main(String[] args) {
//         Connection conn = null;
//         Statement stmt = null;
//         try{
//             // 注册 JDBC 驱动
//             Class.forName(JDBC_DRIVER);
        
//             // 打开链接
//             System.out.println("连接数据库...");
//             conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
//             // 执行查询
//             System.out.println(" 实例化Statement对象...");
//             stmt = conn.createStatement();
//             String sql;
//             sql = "SELECT id, name, url FROM websites";
//             ResultSet rs = stmt.executeQuery(sql);
        
//             // 展开结果集数据库
//             while(rs.next()){
//                 // 通过字段检索
//                 int id  = rs.getInt("id");
//                 String name = rs.getString("name");
//                 String url = rs.getString("url");
    
//                 // 输出数据
//                 System.out.print("ID: " + id);
//                 System.out.print(", 站点名称: " + name);
//                 System.out.print(", 站点 URL: " + url);
//                 System.out.print("\n");
//             }
//             // 完成后关闭
//             rs.close();
//             stmt.close();
//             conn.close();
//         }catch(SQLException se){
//             // 处理 JDBC 错误
//             se.printStackTrace();
//         }catch(Exception e){
//             // 处理 Class.forName 错误
//             e.printStackTrace();
//         }finally{
//             // 关闭资源
//             try{
//                 if(stmt!=null) stmt.close();
//             }catch(SQLException se2){
//             }// 什么都不做
//             try{
//                 if(conn!=null) conn.close();
//             }catch(SQLException se){
//                 se.printStackTrace();
//             }
//         }
//         System.out.println("Goodbye!");
//     }
// }