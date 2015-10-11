package log_in;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class login {
	/*
	 * url ,username , password dang nhap mysql thay doi trong class loadDriver
	 */
	private static final String url = "jdbc:mysql://localhost";
	private static final String user = "root"; 
	private static final String password = "123456";
	public static void DangNhap(){
		support.loadDriver a = new support.loadDriver();
		Connection connect;
		Statement stmt;
		ResultSet result;
		int flag =0;
		
		String userin,passwdin;
		
		String user1,passwd1;
		support.hamnhap nhap = new support.hamnhap();
		try{
			connect = DriverManager.getConnection(url,user,password);
			System.out.println("Get Connect To Database Complete ...\n");	
			stmt = connect.createStatement();
			stmt.executeUpdate("use lab;");
			result = stmt.executeQuery("SELECT * FROM account;");
			

			
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
