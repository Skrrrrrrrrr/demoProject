import java.io.IOException;  
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Properties;  
  
public class MySqlSuperDao implements ISuperDao {  
    /** 
     * 创建数据库(读SQL脚本) 
     *  
     * @author HeCheng 
     */  
    @SuppressWarnings("finally")  
    public boolean createMisDB(String name, String year) throws DbException,  
            DaoException, SQLException {  
        Connection conn = null;  
        Statement stmt = null;  
        boolean success = false;  
        // 创建数据库名  
        String dbName = name + "_" + year;  
        try {  
            List<String> sqlList = new ArrayList<String>();  
            try {  
                InputStream sqlFileIn = MySqlSuperDao.class.getResourceAsStream("/MySql/createDB.sql");  
                // 将SQL脚本产生为list<String>  
  
                StringBuffer sqlSb = new StringBuffer();  
                byte[] buff = new byte[1024];  
                int byteRead = 0;  
                while ((byteRead = sqlFileIn.read(buff)) != -1) {  
                    sqlSb.append(new String(buff, 0, byteRead, "utf-8"));  
                }  
  
                String[] sqlArr = sqlSb.toString().split(  
                        "(;\\s*\\r\\n)|(;\\s*\\n)");  
  
                // 替换数据库名  
                int replace = 0;  
                for (int i = 0; i < sqlArr.length; i++) {  
                    if (replace == 2) {  
                        break;  
                    }  
                    if (sqlArr[i].indexOf("@@@dbName@@@") > -1) {  
                        sqlArr[i] = sqlArr[i].replace("@@@dbName@@@", dbName);  
                        replace++;  
                    }  
                }  
                // 将数组转成LIST并且过滤LOCKTABLE 和注释  
                for (int i = 0; i < sqlArr.length; i++) {  
                    String sql = sqlArr[i].replaceAll("--.*", "").trim();  
                    if (!sql.equals("") && sql.indexOf("LOCK TABLES") != 0  
                            && sql.indexOf("UNLOCK TABLES") != 0  
                            && sql.indexOf("/*") != 0) {  
                        sqlList.add(sql);  
                    }  
                }  
            } catch (Exception e) {  
                System.out.println("error");  
                return false;  
            }  
            // 创建数据库链接并执行SQL脚本  
            conn = this.getConnection();  
            stmt = null;  
            conn.setAutoCommit(false);  
            stmt = conn.createStatement();  
            for (String sql : sqlList) {  
                stmt.addBatch(sql);  
            }  
            stmt.executeBatch();  
            success = true;  
        } catch (Exception e) {  
            // 如果报错，则删除D  
            conn = null;  
            conn = this.getConnection();  
            stmt = null;  
            conn.setAutoCommit(false);  
            stmt = conn.createStatement();  
            stmt.execute("drop database " + dbName);  
            success = false;  
        } finally {  
            if (stmt != null) {  
                stmt.close();  
            }  
            if (conn != null) {  
                conn.close();  
            }  
            return success;  
        }  
    }  
  
    /**  
     * 获取数据库链接  
     *   
     * @author HeCheng  
     * @time 2011-02-26 10:48:24  
     * @return  
     * @throws SQLException  
     * @throws IOException  
     * @throws ClassNotFoundException  
     */  
    private Connection getConnection() throws SQLException, IOException,  
            ClassNotFoundException {  
        Connection con = null;  
        Class.forName("com.mysql.jdbc.Driver");  
  
        Properties properties = new Properties();  
        properties.load(MySqlSuperDao.class  
                .getResourceAsStream("/MySql/DB.properties"));  
        String url = properties.getProperty("url");  
        String u = properties.getProperty("u");  
        String p = properties.getProperty("p");  
        con = DriverManager.getConnection("jdbc:mysql://" + url + "", u, p);  
        return con;  
    }  
}