package ex;
        import android.util.Log;
        import java.sql.*;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.lang.String;
public class SqlHelper {
    //打开数据库连接
    public static Connection openConnection() {
        Connection conn = null;
        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME); //加载数据库驱动
            Log.d("sss", "加载成功 ");
            conn = DriverManager.getConnection("jdbc:mysql:"+"//192.168.1.103:3306/computer?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","root");
            Log.d("sss", "连接成功 ");
        } catch (ClassNotFoundException e) {
            conn = null;
            Log.d("sss", "连接失败1 ");
        } catch (SQLException e) {
            conn = null;
            Log.d("sss", "连接失败2 ");
        }

        return conn;
    }

}

