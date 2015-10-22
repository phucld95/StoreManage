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
import java.util.Scanner;

/**
 *
 * @author luatnguyen88
 */
public class Test_JDBC {

    public static void main(String[] args) {
        String c;
        Scanner input = new Scanner(System.in);
        Tinh_Tien tinh_tien = new Tinh_Tien();
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
            info.setProperty("characterEncoding", "utf8");
            info.put("user", "root");
            info.put("password", "hedspik58");
            Connection conn = DriverManager.getConnection(conString, info);
            Statement st = conn.createStatement();
            int sum = 0;
            do {
                tinh_tien = Tinh_Tien.Tinh_Tien();

                //String SQL = "select cung_cap.Gia_Ban from cung_cap natural join mat_hang"
                String SQL = String.format("select Gia_Ban from cung_cap natural join mat_hang where Ten_San_Pham = N'%s';", tinh_tien.name);
                ResultSet rs = st.executeQuery(SQL);
                while (rs.next()) {
                    int Gia_Ban = rs.getInt("Gia_Ban");
                    sum = sum + Gia_Ban * tinh_tien.so_luong;
                }
                //System.out.println(sum);
                System.out.print("tiep tu nhap mat hang ? (y/n)");
                c = input.nextLine();
            } while (c.equals("y"));
            System.out.println("so tien can tra:" + sum);
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
            int i = st.executeUpdate("update mat_hang set Ten_San_Pham='sữa' where MSMH='1'");
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
