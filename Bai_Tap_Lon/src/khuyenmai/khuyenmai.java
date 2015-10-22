package khuyenmai;
import java.sql.*;

import support.getConnection;
import support.hamnhap;
public class khuyenmai {
	public static void demo() throws SQLException{
		
		support.getConnection b = new support.getConnection();
		support.hamnhap nhap=new support.hamnhap();
		Connection connect;
		connect=b.getConnection();
		Statement stmt;
		ResultSet result;
		int dot;
		
		System.out.println("Nhap theo dinh dang nam/thang/ngay. Nhap ngay :");
		String a = hamnhap.nhapchu();
		try{
			stmt = connect.createStatement();
			
			try{
				result = stmt.executeQuery("select * from khuyen_mai where TGDR <= '"+a+"' and TGKT>='"+a+"';");
				result.next();
				dot = result.getInt("Id_KM");
				System.out.printf("dot thu : "+dot);
			}
			catch(Exception e){
				System.out.println("khong co.");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		connect.close();
		
	}
	
	public static void main(String[] args){
		khuyenmai x = new khuyenmai();
		try {
			x.demo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
