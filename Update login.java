// Chỉnh lại tên package 
package realproject;
import java.sql.*;
import java.util.*;
	

public class loginAccount{
	
	public static void connectToDBMS(){
		
		try{			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load JDBC Driver Successfully!");
		}catch(Exception exc){
			
			exc.printStackTrace();
			
		}		
	}
	public static void DangNhap(Connection myConn){
		
		Statement myStmt=null;
		ResultSet myRs=null;
		
		String userName,currentUserName;
		String passWord,currentPassWord;
		
		int check=0;
		
		try{
			
		// Lấy con trỏ trỏ đến bảng						
		myStmt=myConn.createStatement();
		myRs=myStmt.executeQuery("SELECT * FROM admin");
		
		// Hàm đăng nhập
		
		Scanner inputStream= new Scanner(System.in);
		System.out.println("Enter username\t");
		userName=inputStream.nextLine();
		System.out.println("Enter password\t");
		passWord=inputStream.nextLine();
		inputStream.close();
		
		while(myRs.next()){
			
			currentUserName=myRs.getString("name");
			currentPassWord=myRs.getString("password");
			
			if(userName.compareTo(currentUserName)==0&&passWord.compareTo(currentPassWord)==0) {
				check=1;
				System.out.println("Login successfully!");
			}			
		}
		if(check==0) System.out.println("Login failed ! Check the username and password");
		
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public static void main (String[] args) throws SQLException {
		
		Connection myConn=null;
		
		// Chỉnh lại url và password
		String url="jdbc:mysql://localhost:3306/demo";
		String userName="root";
		String passWord="hoangson316";
		
		myConn=DriverManager.getConnection(url,userName,passWord);
		
		connectToDBMS();
		DangNhap(myConn);
		
	}
}
	
	

	
		
		
		
		
		
		
		
		
		
		
		
		


