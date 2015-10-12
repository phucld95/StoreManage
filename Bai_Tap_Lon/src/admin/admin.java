package admin;

import java.sql.*;
import support.*;

public class admin{
	
	public static void them(){
		Statement stmt;
		ResultSet result;
		Connection connect;
		connect = getConnection.getConnection();
		int flag =0;
		int dem=0;
		int result1;
		
		try {
			stmt = connect.createStatement();
			try{
			result = stmt.executeQuery("select * from account order by STT asc;");
			System.out.println("Tai khoan: ");
			String userin = hamnhap.nhapchu();
			while(result.next()){
				if(userin.equals(result.getString("username"))){
					System.out.println("Da ton tai tai khoan: "+userin);
					flag =1;
					break;
				}
				dem=result.getInt("STT");
			}
			if(flag ==0){
					dem = dem +1;
					System.out.println("Mat Khau: ");
					String passwdin = hamnhap.nhapchu();
					result1 = stmt.executeUpdate("insert into account values('"+dem+"','"+userin+"','"+passwdin+"');");
					System.out.println("Them thanh cong.");
				}
			connect.close();
			}
			
			catch(Exception e){
				System.out.println("khong co.");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void xoa(){
		Statement stmt;
		ResultSet result;
		Connection connect;
		connect = getConnection.getConnection();
		int flag =0;
		int result1;
		
		try {
			stmt=connect.createStatement();
			result =stmt.executeQuery("select username from account");
			System.out.println("Nhap tai khoan muon xoa:\nTai khoan: ");
			String userin = hamnhap.nhapchu();
			while(result.next()){
				if(userin.equals(result.getString("username"))){
					flag =1;
				}
				
			}
			if(flag ==0){
				System.out.println("Khong co tai khoan "+userin);
				
			}
			else{
				result1=stmt.executeUpdate("delete from account where username = '"+userin+"';");
				System.out.println("Xoa thanh cong.");
			}
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sua(){
		Statement stmt;
		ResultSet result;
		Connection connect;
		connect = getConnection.getConnection();
		int flag =0;
		int result1;
		
		try {
			stmt=connect.createStatement();
			result =stmt.executeQuery("select username from account");
			System.out.println("Nhap ten tai khoan muon sua:\nTai khoan: ");
			String userin = hamnhap.nhapchu();
			while(result.next()){
				if(userin.equals(result.getString("username"))){
					flag =1;
				}
			}
			if(flag ==0){
				System.out.println("Khong co tai khoan nay");
			}
			else{
				System.out.println("Mat khau moi: ");
				String passwdin = hamnhap.nhapchu();
				result1 =stmt.executeUpdate("update account set password ='"+passwdin+"'where username ='"+userin+"';");
				System.out.println("Sua thanh cong.");
			}
			connect.close();
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		admin a = new admin();
		int i;
		System.out.println("1.them\n2.xoa\n3.sua\nNhap lua chon:");
		i=hamnhap.nhapso();
		switch (i){
		case 1:{
			a.them(); break;
		}
		case 2: {
			a.xoa();break;
		}
		case 3:{
			a.sua();break;
		}
		}
		
	}
	
	
}
