package business.config;

import java.sql.*;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_practice_eployee";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2005";
    public static Connection openConnection() {
        Connection conn = null;

        try {
            // 1. Set driver kết nối đến CSDL MySQL - Với type3 tro xuong
//            Class.forName(DRIVER);

            // 2. Khởi tạo đối tượng connection từ ứng dụng java đến CSDL
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (SQLException e){
            System.err.println("Có lỗi trong quá trình kết nối với CSDL" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection(Connection con, CallableStatement callSt) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (callSt != null) {
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Connection con = openConnection();
        if (con != null) {
            System.out.println("Kêt nối thành công!");
        }else{
            System.err.println("Kết nối thất bại");
        }
    }
}

