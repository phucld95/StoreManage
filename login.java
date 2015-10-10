package log_in;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class login {
	
	private static String url = "jdbc:mysql://localhost:3306/admin";
	private static String username = "root";
	private static String password = "tranlinh265";

	/*
	 * Load mysql jdbc driver
	 */
	
	public static void connectToDBMS(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load JDBC Driver Complete ...\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Hàm đăng nhập
	 */
	public static void DangNhap(){
		
		Connection connect;
		Statement stmt;
		ResultSet result;
		int flag =0;
		/*
		 * Dữ liệu nhập từ bàn phím
		 * tài khoản :userin
		 * mật khẩu : passwdin
		 */
		String userin,passwdin;
		
		/*
		 * Dữ liệu lấy ra từ bảng Tai_Khoan
		 */
		String user1,passwd1;
		
		/*
		 * khởi tạo hàm nhập dữ liệu tư bàn phím 
		 */
		Scanner nhap = new Scanner(System.in);
		connectToDBMS();
		try{
			connect = DriverManager.getConnection(url,username,password);
			//System.out.println("Get Connect To Database Complete ...\n");	
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT * FROM member;");
			
			//System.out.println("Insert ");
			System.out.print("USER : ");
			userin = nhap.nextLine();
			System.out.print("PASSWORD : ");
			passwdin = nhap.nextLine();
			result.next();
			user1=result.getString("user");
			passwd1=result.getString("passwd");
			if(userin.equals(user1) && passwdin.equals(passwd1)){
				System.out.println("\nWellcome back ADMIN");
				flag =1;
			}
			else {
				while(result.next()){
					user1=result.getString("user");
					passwd1=result.getString("passwd");
					if(userin.equals(user1) && passwdin.equals(passwd1)){
						System.out.println("\nWellcome back "+userin);
						flag =1;
					}
				}
			}
			if(flag == 0){
				System.out.println("\nWrong Username or Password");
			}
			
			connect.close();
			stmt.close();
			result.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		login sql = new login();
		sql.DangNhap();
	}
}
