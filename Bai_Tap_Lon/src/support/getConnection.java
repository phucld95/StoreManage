package support;

import java.sql.Connection;
import java.sql.DriverManager;

public class getConnection {
	
	public static Connection getConnection(){
		Connection connect;
		support.loadDriver a = new support.loadDriver();
		try{
			connect = DriverManager.getConnection(a.url,a.username,a.password);
			//System.out.println("Get Connect To Database Complete ...\n");	
			return connect;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
}
