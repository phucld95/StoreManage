package support;

import java.sql.DriverManager;

public class loadDriver {
	
	
	public static String url = "jdbc:mysql://localhost:3306/demo2";
	public static String username = "root";
	public static String password = "tranlinh265";
	public static void connectToDBMS(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Load JDBC Driver Complete ...\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
