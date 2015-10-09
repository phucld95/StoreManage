/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_JDBC;

import static com.oracle.util.Checksums.update;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author luatnguyen88
 */
public class Test_JDBC {

    public static void main(String[] args) {
        try {
            //đăng ký driver
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            //Class.forName("com.mysql.jdbc.Driver"); cần throws classNotFoundException
            //tạo đối tượng kết nối
            //cách 1
            //DriverManager.getConnection("jdbc:mysql://localhost:3306/Sieu_Thi_VN","root","hedspik58");
            //cách 2
            String conString = "jdbc:mysql://localhost:3306/sieu_thi_vn";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "hedspik58");
            Connection conn = DriverManager.getConnection(conString, info);
            Statement st = conn.createStatement();
            String SQL = "select * from mat_hang";
            ResultSet rs = st.executeQuery(SQL);
            System.out.println("truoc khi update");
            while (rs.next()) {
                int MSMH = rs.getInt("MSMH");
                int MSNH = rs.getInt("MSNH");
                String Ten_San_Pham = rs.getString("Ten_San_Pham");

                //Display values
                System.out.print("MSMH: " + MSMH);
                System.out.print(", MSNH: " + MSNH);
                System.out.print(", Ten San Pham: " + Ten_San_Pham + "\n");
            }
            int i = st.executeUpdate("update mat_hang set Ten_San_Pham='may tinh bang' where MSMH='4'");
            rs = st.executeQuery(SQL);
            System.out.println("sau khi update");
            while (rs.next()) {
                //Retrieve by column name
                int MSMH = rs.getInt("MSMH");
                int MSNH = rs.getInt("MSNH");
                String Ten_San_Pham = rs.getString("Ten_San_Pham");

                //Display values
                System.out.print("MSMH: " + MSMH);
                System.out.print(", MSNH: " + MSNH);
                System.out.print(", Ten San Pham: " + Ten_San_Pham + "\n");
            }
            //Tinh_Tien.Tinh_Tien();
        } catch (SQLException ex) {
            System.out.println(ex);

        }

    }
}
