package tinh_tien;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class cash {
	public static void main(String[] args ){
		Data Sale_Data = new Data(); 
		Scanner nhap = new Scanner(System.in);
		String c;
		try {
			//kết nối database
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String conString = "jdbc:mysql://localhost:3306/sieu_thi_vn";
            Properties info = new Properties();
            info.setProperty("characterEncoding", "utf8");
            info.put("user", "root");
            info.put("password", "hedspik58");
            Connection conn = DriverManager.getConnection(conString, info);
            Statement st = conn.createStatement();
            //nhập mặt hàng và đưa ra số tiền phải trả
            do {
                Sale_Data.InputData();
                String SQL = String.format("select Gia_Ban from cung_cap natural join mat_hang where Ten_San_Pham = '%s';", Sale_Data.name);
                ResultSet rs = st.executeQuery(SQL);
                while (rs.next()) {
                    int Gia_Ban = rs.getInt("Gia_Ban");
                    Sale_Data.sum = Sale_Data.sum + Gia_Ban * Sale_Data.so_luong;
                }
                System.out.print("tiep tu nhap mat hang ? (y/n)");
                c = nhap.nextLine();
            } while (c.equals("y"));
            System.out.println("so tien can tra:" + Sale_Data.sum+"VNĐ");
        } catch (SQLException ex) {
        	System.out.println(ex);
        }
	}
}
//tạo dữ liệu cho các mặt hàng
class Data {
    String name;
    int so_luong=0,sum=0;
    private Scanner Input ;
   public void InputData() {
	   Input = new Scanner(System.in);
       System.out.print("nhap ten mat hang:");
       name = Input.nextLine();
       System.out.print("nhap so luong:");
       so_luong=Input.nextInt();
   }
}