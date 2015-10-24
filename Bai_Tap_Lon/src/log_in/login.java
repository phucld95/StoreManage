package log_in;

import java.sql.*;

import admin.admin;


public class login {
	/*
	 * url ,username , password dang nhap mysql thay doi trong class loadDriver
	 */
	public static void DangNhap(){
		support.loadDriver a = new support.loadDriver();
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
		support.hamnhap nhap = new support.hamnhap();
		a.connectToDBMS();
		try{
			connect = DriverManager.getConnection(a.url,a.username,a.password);
			//System.out.println("Get Connect To Database Complete ...\n");	
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT * FROM account order by STT asc;");
			
			//System.out.println("Insert ");
			System.out.print("USER : ");
			userin = nhap.nhapchu();
			System.out.print("PASSWORD : ");
			passwdin = nhap.nhapchu();
			result.next();
			user1=result.getString("username");
			passwd1=result.getString("password");
			if(userin.equals(user1) && passwdin.equals(passwd1) ){
				System.out.println("\nWellcome back ADMIN");
				flag =1;
			}
			else {
				while(result.next()){
					user1=result.getString("username");
					passwd1=result.getString("password");
					if(userin.equals(user1) && passwdin.equals(passwd1)){
						System.out.println("\nWellcome back "+userin);
						flag =1;
						admin.them();
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
